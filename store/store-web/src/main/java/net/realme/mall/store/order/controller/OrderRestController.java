package net.realme.mall.store.order.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.realme.framework.cache.redis.RedisCache;
import net.realme.framework.sso.ColorOSUser;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultT;
import net.realme.framework.util.text.UrlUtil;
import net.realme.framework.web.controller.LocalizeController;
import net.realme.id.domain.IdBizCode;
import net.realme.id.facade.IdGenerator;
import net.realme.mall.basics.consts.CacheConsts;
import net.realme.mall.basics.dto.CurrencyDto;
import net.realme.mall.basics.dto.SiteDto;
import net.realme.mall.basics.facade.CurrencyService;
import net.realme.mall.basics.facade.IndiaPinCodeService;
import net.realme.mall.basics.facade.SiteService;
import net.realme.mall.order.consts.OrderConsts;
import net.realme.mall.order.domain.*;
import net.realme.mall.order.facade.OrderService;
import net.realme.mall.payment.constant.PaymentConstant;
import net.realme.mall.payment.domain.OrderPaymentDto;
import net.realme.mall.payment.domain.PaymentRequestConstant;
import net.realme.mall.product.domain.response.ProductSkuResponse;
import net.realme.mall.product.domain.response.SkuAttributeInSkuResponse;
import net.realme.mall.product.facade.ProductSkuService;
import net.realme.mall.store.common.consts.BasicMapping;
import net.realme.mall.store.common.consts.StoreOrderConsts;
import net.realme.mall.store.domain.order.BuyOrderSkuInfo;
import net.realme.mall.store.domain.order.OrderRequestConstant;
import net.realme.mall.store.domain.order.req.*;
import net.realme.mall.store.domain.order.rsp.OrderConfirmInfo;
import net.realme.mall.store.domain.order.rsp.OrderConfirmItems;
import net.realme.mall.store.domain.order.rsp.OrderQueryItemRsp;
import net.realme.mall.store.domain.order.rsp.OrderQueryRsp;
import net.realme.mall.user.domain.UserAddressDto;
import net.realme.mall.user.facade.UserAddressService;
import net.realme.shared.config.inventory.InventorySharedConstant;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.rocketmq.spring.starter.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.store.order.controller
 *
 * @author 91000044
 * @date 2018/9/1 19:10
 */
@Api(tags = {"订单管理"})
@Validated
@RestController
public class OrderRestController extends LocalizeController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String SKU_ATTR_COLOR = "Color";
    private static final String SKU_ATTR_SPEC = "Spec";

    @Autowired
    private RedisCache cache;

    @Autowired
    private RedisCache<Integer> cacheInteger;

    @Autowired
    private RedisCache<List<BuyOrderSkuInfo>> cacheBuy;

    @Autowired
    private ProductSkuService productSkuService;

    @Autowired
    private UserAddressService userAddressService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private IndiaPinCodeService indiaPinCodeService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private SiteService siteService;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private IdGenerator idGenerator;

    @Value("${api.url.prefix}")
    private String apiUrlPrefix;

    @Value("${payment.url.prefix}")
    private String paymentUrlPrefix;


    /**
     * 立即购买
     * @return
     */
    @ApiOperation(value = "立即购买")
    @PostMapping("/order/stage")
    public Result stage(@PathVariable String siteCode, @RequestBody List<BuyOrderSkuInfoReq> buyItemList) {
        //todo
        // 检查sku各属性, 如时间 价格 每单上限等
        // 检查库存
        // 区分购买方式 购物车、直接
        // 购物车设计 => 暂存区设计 有效时间
        Long userId = ColorOSUser.getCurrentId();
        logger.info("order stage begin, userId:[{}], params:[{}].", userId, buyItemList);
        logger.info("this obj:[{}]", this);
       try {
           // 限制频繁请求
//           if (isLimited(userId)) {
//               return err(StoreOrderConsts.ErrorInfo.REQUEST_FREQUENT_LIMIT_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.REQUEST_FREQUENT_LIMIT_MSG));
//           }

           long time1 = System.currentTimeMillis();
           if (CollectionUtils.isEmpty(buyItemList)) {
               return err(StoreOrderConsts.ErrorInfo.BUY_ORDER_ITEM_EMPTY_CODE, this.getMsgByCode(StoreOrderConsts.ErrorInfo.BUY_ORDER_ITEM_EMPTY_MSG));
           }
           int orderCount = 0;
           List<BuyOrderSkuInfo> cacheItemList = new ArrayList<>();
           for (BuyOrderSkuInfoReq param : buyItemList) {
               Integer skuId = param.getSkuId();
               BigDecimal price = param.getPrice();
               int count = param.getCount();
               // 参数验证
               if (skuId==null) {
                   return err(StoreOrderConsts.ErrorInfo.SKU_ID_NULL_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.SKU_ID_NULL_MSG));
               }
               if (price==null) {
                   return err(StoreOrderConsts.ErrorInfo.PRICE_NULL_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.PRICE_NULL_MSG));
               }
               if (count<=0) {
                   return err(StoreOrderConsts.ErrorInfo.COUNT_ZERO_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.COUNT_ZERO_MSG));
               }
               // SKU信息获取
               ResultT<ProductSkuResponse> skuDtoResult = productSkuService.getSkuInfoById(skuId);
               ProductSkuResponse skuDto = skuDtoResult.getData();
               if (skuDto == null) {
                   return err(StoreOrderConsts.ErrorInfo.SKU_NOT_FOUND_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.SKU_NOT_FOUND_MSG));
               }
               // 当天购买SPU检查
               String userProdLimitKey = String.format(OrderConsts.USER_PROD_DAY_LIMIT, userId, skuDto.getProductId());
               Integer userProdCache = cacheInteger.get(userProdLimitKey);
               if (userProdCache != null) {
//                   return err(StoreOrderConsts.ErrorInfo.USER_PROD_DAY_LIMIT_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.USER_PROD_DAY_LIMIT_MSG));
               }

               // 库存检查
               String invKey = String.format(InventorySharedConstant.CACHE_SKU_LOGIC_INVENTORY, skuId);
               Long inv = cache.incr(invKey, 0L);
               if (inv != null && inv ==0L) {
                   return err(StoreOrderConsts.ErrorInfo.SKU_INV_ZERO_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.SKU_INV_ZERO_MSG));
               }
               if (inv == null || inv < count) {
                   return err(StoreOrderConsts.ErrorInfo.INSUFFICIENT_INV_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.INSUFFICIENT_INV_MSG));
               }
               // 验证价格
               if (price.compareTo(skuDto.getPrice()) != 0) {
                   return err(StoreOrderConsts.ErrorInfo.SKU_PRICE_INVALID_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.SKU_PRICE_INVALID_MSG));
               }
               // 单sku上限
               if (count > skuDto.getMaxQuantity()) {
                   return err(StoreOrderConsts.ErrorInfo.SKU_NUM_UP_LIMIT_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.SKU_NUM_UP_LIMIT_MSG));
               }
               // 商品已下架
               if (skuDto.getShelfStatus()==1) {
                   return err(StoreOrderConsts.ErrorInfo.OFF_SHELF_STATUS_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.OFF_SHELF_STATUS_MSG));
               }

               if (!StoreOrderConsts.SkuType.ACCESSORY.equals(param.getSkuType())) {// 配件不校验
                   // 商品是否开卖
                   if (skuDto.getSaleStatus()!=1) {
                       return err(StoreOrderConsts.ErrorInfo.NOT_SALE_STATUS_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.NOT_SALE_STATUS_MSG));
                   }
                   // 是否购买时间段
                   long saleStart = skuDto.getSaleStart().longValue();
                   long saleEnd = skuDto.getSaleEnd();
                   long now = System.currentTimeMillis();
                   if (now < saleStart || now > saleEnd) {
                       return err(StoreOrderConsts.ErrorInfo.NON_PURCHASE_PERIOD_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.NON_PURCHASE_PERIOD_MSG));
                   }
               }

               orderCount += count;

               BuyOrderSkuInfo buyOrderSkuInfo = new BuyOrderSkuInfo();
               buyOrderSkuInfo.setSkuId(skuId);
               buyOrderSkuInfo.setPrice(price);
               buyOrderSkuInfo.setCount(count);
               buyOrderSkuInfo.setSkuType(param.getSkuType());
               cacheItemList.add(buyOrderSkuInfo);
           }
           // 每单上限
           if (orderCount > StoreOrderConsts.ORDER_UP_LIMIT) {
               return err(StoreOrderConsts.ErrorInfo.ORDER_UP_LIMIT_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.ORDER_UP_LIMIT_MSG));
           }
           // 购买订单的商品明细存放到redis
           String cacheOrderSkuKey = String.format(OrderConsts.CACHE_ORDER_SKU, userId, StoreOrderConsts.ReidsCache.MINUTE_30);
           cacheBuy.set(cacheOrderSkuKey, cacheItemList);

           long time2 = System.currentTimeMillis();
           logger.info("order stage end, userId:[{}], params:[{}], cost:[{}]ms.", userId, buyItemList, time2-time1);

           return ok(UrlUtil.join("/" + siteCode, OrderRequestConstant.CHECKOUT));
       } catch (Exception e) {
           logger.error("order stage fail, userId:[{}], params:[{}], errMsg:[{}]", userId, buyItemList, ExceptionUtils.getStackTrace(e));
           return err(StoreOrderConsts.ErrorInfo.ORDER_STAGE_ERR_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.ORDER_STAGE_ERR_MSG));
       }
    }

    /**
     * 订单购买明细获取
     * @return
     */
    @ApiOperation(value = "商品列表-订单确认页面")
    @GetMapping("/order/items")
    public Result getOrderItems(@PathVariable String siteCode) {
        Long userId = ColorOSUser.getCurrentId();
        logger.info("get order items begin, userId:[{}].", userId);
       try {
           long time1 = System.currentTimeMillis();
           OrderConfirmInfo orderConfirmInfo = new OrderConfirmInfo();
           List<OrderConfirmItems> orderItemList = new ArrayList<>();
           // 从redis获取订单明细
           String cacheOrderSkuKey = String.format(OrderConsts.CACHE_ORDER_SKU, userId);
           List<BuyOrderSkuInfo> buyItemList = cacheBuy.get(cacheOrderSkuKey);
           if (CollectionUtils.isEmpty(buyItemList)) {
               return err(StoreOrderConsts.ErrorInfo.NO_STAGE_ITEMS_ERR_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.NO_STAGE_ITEMS_ERR_MSG));
           }
           int totalCount = 0;
           BigDecimal totalAmount = new BigDecimal(0);
           for (BuyOrderSkuInfo cacheItem : buyItemList) {
               Integer skuId = cacheItem.getSkuId();
               Integer count = cacheItem.getCount();
               // SKU信息获取
               ResultT<ProductSkuResponse> skuDtoResult = productSkuService.getSkuInfoById(skuId);
               ProductSkuResponse skuDto = skuDtoResult.getData();
               if (skuDto == null) {
                   return err(StoreOrderConsts.ErrorInfo.SKU_NOT_FOUND_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.SKU_NOT_FOUND_MSG));
               }
               OrderConfirmItems orderItemInfo = new OrderConfirmItems();
               orderItemInfo.setSkuId(skuId);
               orderItemInfo.setSkuName(skuDto.getSkuName());
               String imgUrl = null;
               try {
                   imgUrl = skuDto.getOverviewUri().split(";")[0];
               } catch (Exception e) {
                   imgUrl = "";
               }

               orderItemInfo.setImageUrl(imgUrl);
               List<SkuAttributeInSkuResponse> selOption = skuDto.getSelOption();
               if (!CollectionUtils.isEmpty(selOption)) {
                   for (SkuAttributeInSkuResponse skuAttributeInSkuResponse : selOption) {
                       if(SKU_ATTR_COLOR.equals(skuAttributeInSkuResponse.getAttrName())) {
                           orderItemInfo.setColor(skuAttributeInSkuResponse.getAttrVal());
                       }
                       if(SKU_ATTR_SPEC.equals(skuAttributeInSkuResponse.getAttrName())) {
                           orderItemInfo.setSkuSpec(skuAttributeInSkuResponse.getAttrVal());
                       }
                   }
               }
               orderItemInfo.setCount(count);
               orderItemInfo.setPrice(skuDto.getPrice());
               totalCount += count;
               totalAmount = totalAmount.add(skuDto.getPrice().multiply(new BigDecimal(count)));
               orderItemList.add(orderItemInfo);
           }
//           orderConfirmInfo.setDeliveryFee(new BigDecimal(0));
           orderConfirmInfo.setQuantity(totalCount);
           orderConfirmInfo.setTotalAmount(totalAmount);
           // 获取货币符号
           String symbol = getCurrencySymbol(siteCode);
           orderConfirmInfo.setCurrencySymbol(symbol);
//           orderConfirmInfo.setPayAmount(totalAmount);
           orderConfirmInfo.setOrderItemList(orderItemList);

           long time2 = System.currentTimeMillis();
           logger.info("get order items end, userId:[{}], cost:[{}]ms.", userId, time2-time1);
           return ok(orderConfirmInfo);
       } catch (Exception e) {
           logger.error("get order items fail, userId:[{}], errMsg:[{}]", userId, ExceptionUtils.getStackTrace(e));
           return err(StoreOrderConsts.ErrorInfo.GET_ORDER_ITEMS_ERR_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.GET_ORDER_ITEMS_ERR_MSG));
       }
    }

    /**
     * 下单
     * @return
     */
    @ApiOperation(value = "提交订单")
    @PostMapping("/order/create")
    public Result create(@PathVariable String siteCode, @RequestBody SubmitOrderInfoReq submitOrderInfoReq, HttpServletRequest request) {
        // 检查sku各属性, 如时间 价格 每单上限等
        // 检查及扣减库存
        // 发送下单异步消息（可选）
        Long userId = ColorOSUser.getCurrentId();
        long time1 = System.currentTimeMillis();
        logger.info("order create begin, userId:[{}].", userId);
        try {
            // 地址检查
            SubmitOrderAddressReq submitAddress = submitOrderInfoReq.getAddress();
            if (submitAddress == null) {
                return err(StoreOrderConsts.ErrorInfo.PLACE_ORDER_ADDRESS_NULL_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.PLACE_ORDER_ADDRESS_NULL_MSG));
            }
            if (submitAddress.getId() == null) {
                return err(StoreOrderConsts.ErrorInfo.PLACE_ORDER_ADDRESS_NULL_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.PLACE_ORDER_ADDRESS_NULL_MSG));
            }
            Long addressId = submitAddress.getId();
            ResultT<UserAddressDto> addressResult = userAddressService.getById(userId, addressId);
            if (!addressResult.isSuccess()) {
                return err(StoreOrderConsts.ErrorInfo.ADDRESS_NULL_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.ADDRESS_NULL_MSG));
            }
            UserAddressDto address = addressResult.getData();
            if (null == address) {
                return err(StoreOrderConsts.ErrorInfo.ADDRESS_NULL_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.ADDRESS_NULL_MSG));
            }
            if (!userId.equals(address.getSsoid())) {
                return err(StoreOrderConsts.ErrorInfo.ADDRESS_NULL_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.ADDRESS_NULL_MSG));
            }

            // 商品信息检查
            SubmitOrderItemInfoReq orderConfirmInfo = submitOrderInfoReq.getOrderConfirmInfo();
            if (orderConfirmInfo == null) {
                return err(StoreOrderConsts.ErrorInfo.PLACE_ORDER_ITEM_NULL_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.PLACE_ORDER_ITEM_NULL_MSG));
            }
            List<SubmitItemsReq> itemList = orderConfirmInfo.getOrderItemList();
            if (itemList == null) {
                return err(StoreOrderConsts.ErrorInfo.PLACE_ORDER_ITEM_NULL_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.PLACE_ORDER_ITEM_NULL_MSG));
            }

            // redis获取购买订单的商品明细
            String cacheOrderSkuKey = String.format(OrderConsts.CACHE_ORDER_SKU, userId);
            List<BuyOrderSkuInfo> cacheItemList = cacheBuy.get(cacheOrderSkuKey);
            if (null == cacheItemList) {
                return err(StoreOrderConsts.ErrorInfo.ORDER_CHECKOUT_EXPIRED_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.ORDER_CHECKOUT_EXPIRED_MSG));
            }
            // 存放SKU信息
            Map<Integer, ProductSkuResponse> skuMap = new HashMap<>();
            int orderCount = 0;
            BigDecimal totalAmount = new BigDecimal(0);
            for (BuyOrderSkuInfo buyOrderSkuInfo : cacheItemList) {
                Integer skuId = buyOrderSkuInfo.getSkuId();
                BigDecimal price = buyOrderSkuInfo.getPrice();
                // 参数验证
                int count = buyOrderSkuInfo.getCount();
                // SKU信息获取
                ResultT<ProductSkuResponse> skuDtoResult = productSkuService.getSkuInfoById(skuId);
                ProductSkuResponse skuDto = skuDtoResult.getData();
                if (skuDto == null) {
                    return err(StoreOrderConsts.ErrorInfo.SKU_NOT_FOUND_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.SKU_NOT_FOUND_MSG));
                }
                skuMap.put(skuId, skuDto);
                // 库存检查
                String invKey = String.format(InventorySharedConstant.CACHE_SKU_LOGIC_INVENTORY, skuId);
                Long inv = cache.incr(invKey, 0L);
                if (inv == null || inv < count) {
                    return err(StoreOrderConsts.ErrorInfo.INSUFFICIENT_INV_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.INSUFFICIENT_INV_MSG));
                }
                // 商品已下架
                if (skuDto.getShelfStatus()==1) {
                    return err(StoreOrderConsts.ErrorInfo.OFF_SHELF_STATUS_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.OFF_SHELF_STATUS_MSG));
                }

                if (!StoreOrderConsts.SkuType.ACCESSORY.equals(buyOrderSkuInfo.getSkuType())) {// 配件不校验
                    // 商品是否开卖
                    if (skuDto.getSaleStatus()!=1) {
                        return err(StoreOrderConsts.ErrorInfo.NOT_SALE_STATUS_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.NOT_SALE_STATUS_MSG));
                    }
                    // 是否购买时间段
                    long saleStart = skuDto.getSaleStart().longValue();
                    long saleEnd = skuDto.getSaleEnd();
                    long now = System.currentTimeMillis();
                    if (now < saleStart || now > saleEnd) {
                        return err(StoreOrderConsts.ErrorInfo.NON_PURCHASE_PERIOD_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.NON_PURCHASE_PERIOD_MSG));
                    }
                }

                orderCount += count;
                totalAmount = totalAmount.add(skuDto.getPrice().multiply(new BigDecimal(count)));
            }
            // 每单上限
            Integer orderLimitNum = cacheInteger.get(StoreOrderConsts.ORDER_UP_LIMIT_CACHE_KEY);
            if (orderLimitNum == null) {
                orderLimitNum = StoreOrderConsts.ORDER_UP_LIMIT;
            }
            if (orderCount > orderLimitNum) {
                return err(StoreOrderConsts.ErrorInfo.ORDER_UP_LIMIT_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.ORDER_UP_LIMIT_MSG));
            }

            // 验证价格是否一致
            if (totalAmount.compareTo(orderConfirmInfo.getTotalAmount()) != 0) {
                return err(StoreOrderConsts.ErrorInfo.ORDER_PRICE_INVALID_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.ORDER_PRICE_INVALID_MSG));
            }

//            ColorOSUser user = ColorOSUser.getCurrent();
            // 组装订单信息对象
            // 订单主信息
            ResultT<Long> idGeneratorResult = idGenerator.get(IdBizCode.ORDER, 1);
            if (!idGeneratorResult.isSuccess()) {
                logger.error("generate id for order failed.");
                return null;
            }
            long orderNo = idGeneratorResult.getData();
            OrderMainDto orderDto = new OrderMainDto();
            orderDto.setUserId(userId);
//            orderDto.setUserName(user.getUsername());
            orderDto.setOrderNo(orderNo);
            orderDto.setSiteCode(siteCode);
            orderDto.setOrderType(OrderConsts.OrderType.NORMAL);
            orderDto.setHoldType(OrderConsts.HoldType.NORMAL);
            orderDto.setOrderStatus(OrderConsts.OrderStatus.UNPAID);
            orderDto.setSkuOriginAmount(totalAmount);
            orderDto.setSkuDiscountAmount(new BigDecimal(0));
            BigDecimal deliveryFee = new BigDecimal(0);
            BigDecimal payAmount = totalAmount.subtract(deliveryFee);
            orderDto.setOrderTotalAmount(orderConfirmInfo.getTotalAmount());
            orderDto.setShippingNowFee(deliveryFee);
            orderDto.setShippingOriginFee(deliveryFee);
            orderDto.setPayChannel(PaymentConstant.CHANNEL_BILLDESK);
            getCountry(siteCode, orderDto);
            // 获取货币符号
            String symbol = getCurrencySymbol(siteCode);
            orderDto.setCurrencySymbol(symbol);
//            orderDto.setCurrency("");

            // 订单商品明细
            List<OrderItemDto> itemDtos = new ArrayList<>();
            for (BuyOrderSkuInfo buyOrderSkuInfo : cacheItemList) {
                OrderItemDto item = new OrderItemDto();
                item.setOrderNo(orderNo);
                ProductSkuResponse skuDto = skuMap.get(buyOrderSkuInfo.getSkuId());
                item.setSkuId(skuDto.getSkuId());
                item.setSkuName(skuDto.getSkuName());
                item.setProductId(skuDto.getProductId());
                String imgUrl = null;
                try {
                    imgUrl = skuDto.getOverviewUri().split(";")[0];
                } catch (Exception e) {
                    imgUrl = "";
                }
                item.setImageUrl(imgUrl);
                List<SkuAttributeInSkuResponse> selOption = skuDto.getSelOption();
                if (!CollectionUtils.isEmpty(selOption)) {
                    for (SkuAttributeInSkuResponse skuAttributeInSkuResponse : selOption) {
                        if(SKU_ATTR_COLOR.equals(skuAttributeInSkuResponse.getAttrName())) {
                            item.setColor(skuAttributeInSkuResponse.getAttrVal());
                        }
                        if(SKU_ATTR_SPEC.equals(skuAttributeInSkuResponse.getAttrName())) {
                            item.setSkuSpec(skuAttributeInSkuResponse.getAttrVal());
                        }
                    }
                }
                item.setErpCode(skuDto.getEanCode());
                item.setEanCode(skuDto.getEanCode());
                item.setItemType(1);
                item.setOriginPrice(skuDto.getPrice());
                item.setNowPrice(skuDto.getPrice());
                item.setSkuCount(buyOrderSkuInfo.getCount());
                itemDtos.add(item);
            }
            orderDto.setItems(itemDtos);

            //　物流信息
            OrderDeliveryDto orderDeliveryDto = new OrderDeliveryDto();
            orderDeliveryDto.setOrderNo(orderNo);
            orderDeliveryDto.setFullName(address.getFullName());// 全名
            orderDeliveryDto.setProvince(address.getProvinceName());
            orderDeliveryDto.setCity(address.getCityName());
            orderDeliveryDto.setPinCode(address.getPinCode());
            orderDeliveryDto.setPhoneAreacode(address.getPhoneCallingCodes());
            orderDeliveryDto.setAddress1(address.getAddress1());
            orderDeliveryDto.setAddress2(address.getAddress2());
            orderDeliveryDto.setPhone(address.getPhoneNumber());
            orderDeliveryDto.setEmail(address.getEmail());
            orderDto.setDelivery(orderDeliveryDto);

            // 支付信息
            OrderPaymentDto orderPaymentDto = new OrderPaymentDto();
            orderPaymentDto.setVersion(1);
            ResultT<Long> idGeneratorResult2 = idGenerator.get(IdBizCode.PAYMENT, 1);
            if (!idGeneratorResult2.isSuccess()) {
                logger.error("generate id for payment failed.");
                return null;
            }
            long paymentNo = idGeneratorResult2.getData();
            orderPaymentDto.setPaymentNo(paymentNo);
            orderPaymentDto.setBizNo(orderNo);
            orderPaymentDto.setSsoid(userId);
            orderPaymentDto.setOrderNo(orderNo);
            orderPaymentDto.setPayChannel(PaymentConstant.CHANNEL_BILLDESK);
            orderPaymentDto.setTxnAmount(orderDto.getOrderTotalAmount());
            orderPaymentDto.setBizType(PaymentConstant.BIZ_TYPE_REGULAR);
            orderPaymentDto.setStatus(PaymentConstant.STATUS_PENDING);
            orderPaymentDto.setCreatedAt(System.currentTimeMillis());
            orderDto.setOrderPaymentDto(orderPaymentDto);

            // 扩展信息
            OrderExtDto orderExt = new OrderExtDto();
            orderExt.setOrderNo(orderDto.getOrderNo());
            String ip = request.getRemoteAddr();
            String userAgent = request.getHeader("user-agent");
            orderExt.setIp(ip);
            orderExt.setUa(userAgent);
            orderDto.setOrderExt(orderExt);

            String orderJson = JSON.toJSONString(orderDto);
            logger.info("send user orderJson to MQ, userId:[{}], orderJson:[{}].", userId, orderJson);

            try {
                rocketMQTemplate.convertAndSend("order-topic", orderJson);
            } catch (Exception e) {
                logger.error("send order create msg to mq fail, userId:[{}], errMsg:[{}]", userId, ExceptionUtils.getStackTrace(e));
                return err(StoreOrderConsts.ErrorInfo.ORDER_CREATE_SEND_MQ_ERR_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.ORDER_CREATE_SEND_MQ_ERR_MSG));
            }

            long time2 = System.currentTimeMillis();
            logger.info("order create end, userId:[{}], cost:[{}]ms.", userId, time2-time1);
            return ok(String.valueOf(orderNo));
        } catch (Exception e) {
            logger.error("order create fail, userId:[{}], errMsg:[{}]", userId, ExceptionUtils.getStackTrace(e));
            return err(StoreOrderConsts.ErrorInfo.ORDER_CREATE_ERR_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.ORDER_CREATE_ERR_MSG));
        }

    }

    /**
     * 设置国家
     * @param siteCode
     * @param orderDto
     */
    private void getCountry(@PathVariable String siteCode, OrderMainDto orderDto) {
        String country = "";
        try {
            ResultT<SiteDto> siteResult = siteService.getBySiteCode(siteCode);
            if (siteResult.isSuccess()) {
                country = siteResult.getData()==null?"":siteResult.getData().getCountry();
            }
        } catch (Exception e) {
            country = BasicMapping.countryMap.get(siteCode);
            logger.error("get site country errMsg:[{}]", ExceptionUtils.getStackTrace(e));
        }
        orderDto.setCountry(country);
    }

    /**
     * 订单状态轮询
     * @return
     */
    @ApiOperation(value = "订单状态轮询")
    @GetMapping("/order/polling")
    public Result polling(@PathVariable String siteCode, Long orderNo) {
        try {
            // redis查看订单生成标示
            String invKey = String.format(OrderConsts.CACHE_ORDER_REATED, orderNo);
            Integer createdFlag = cacheInteger.get(invKey);
            if (createdFlag != null) {
                if (createdFlag == OrderConsts.OrderCreateFlag.FAIL_INV_INSUFFICIENT) {
                    return err(StoreOrderConsts.ErrorInfo.ORDER_CREATED_INSUFFICIENT_INV_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.ORDER_CREATED_INSUFFICIENT_INV_MSG));
                }
                String payUrl = UrlUtil.join(apiUrlPrefix, siteCode, PaymentRequestConstant.PAYMENT_REDIRECT + "?orderNo=" + orderNo);
                return ok(payUrl);
            } else {
                return err(StoreOrderConsts.ErrorInfo.ORDER_NOT_YET_CREATED_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.ORDER_NOT_YET_CREATED_MSG));
            }
        } catch (Exception e) {
            logger.error("order polling fail, orderNo:[{}], errMsg:[{}]", orderNo, ExceptionUtils.getStackTrace(e));
            return err(StoreOrderConsts.ErrorInfo.ORDER_POLLING_ERR_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.ORDER_POLLING_ERR_MSG));
        }
    }

    /**
     * 我的订单
     * @return
     */
    @ApiOperation(value = "我的订单")
    @GetMapping("/order/query")
    public Result getOrderInfo(@PathVariable String siteCode) {
        Long userId = ColorOSUser.getCurrentId();
        logger.info("order query begin, userId:[{}].", userId);
        long time1 = System.currentTimeMillis();
        List<OrderQueryRsp> orderQueryRspList = null;
        try {
            // 限制频繁请求
//            if (isLimited(userId)) {
//                return err(StoreOrderConsts.ErrorInfo.REQUEST_FREQUENT_LIMIT_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.REQUEST_FREQUENT_LIMIT_MSG));
//            }

            String orderListKey = String.format(OrderConsts.USER_ORDER_LIST, userId);
            orderQueryRspList = (List<OrderQueryRsp>)cache.get(orderListKey);
            if (!CollectionUtils.isEmpty(orderQueryRspList)) {
                return ok(orderQueryRspList);
            } else {
                orderQueryRspList = new ArrayList<>();
            }
            // 获取货币符号
            String symbol = getCurrencySymbol(siteCode);
            ResultT<List<OrderMainDto>> result = orderService.getOrderInfo(userId);
            if (result.isSuccess()) {
                long time2 = System.currentTimeMillis();
                logger.info("order query end, userId:[{}], cost:[{}]ms.", userId, time2-time1);
                List<OrderMainDto> orderMainDtoList =  result.getData();
                if (!CollectionUtils.isEmpty(orderMainDtoList)) {
                    for (OrderMainDto orderMainDto : orderMainDtoList) {
                        Long orderNo = orderMainDto.getOrderNo();
                        OrderQueryRsp orderQueryRsp = new OrderQueryRsp();
                        orderQueryRsp.setOrderNo(orderNo);
                        orderQueryRsp.setOrderStatus(orderMainDto.getOrderStatus());
                        if (OrderConsts.OrderStatus.UNPAID == orderMainDto.getOrderStatus()) {
                            // 未支付，计算倒计时
                            Long expiredTime = Optional.ofNullable(orderMainDto.getExpiredTime()).orElse(0L);
                            long countDown =expiredTime - System.currentTimeMillis();
                            countDown = countDown<0?0:countDown;
                            orderQueryRsp.setCountDown(countDown/1000);
                        }
//                        orderQueryRsp.setCreateTime(new Date(orderMainDto.getCreateTime()));
                        orderQueryRsp.setCreateTime(orderMainDto.getCreateTime());
                        orderQueryRspList.add(orderQueryRsp);
                        List<OrderItemDto> itemsList = orderMainDto.getItems();
                        List<OrderQueryItemRsp> orderQueryItemRspList = new ArrayList<>();
                        orderQueryRsp.setItems(orderQueryItemRspList);
                        if (!CollectionUtils.isEmpty(itemsList)) {
                            for (OrderItemDto orderItemDto : itemsList) {
                                OrderQueryItemRsp orderQueryItemRsp = new OrderQueryItemRsp();
                                orderQueryItemRsp.setImageUrl(orderItemDto.getImageUrl());
                                orderQueryItemRsp.setSkuId(orderItemDto.getSkuId());
                                orderQueryItemRsp.setSkuName(orderItemDto.getSkuName());
                                orderQueryItemRsp.setColor(orderItemDto.getColor());
                                orderQueryItemRsp.setSkuSpec(orderItemDto.getSkuSpec());
                                orderQueryItemRsp.setSkuCount(orderItemDto.getSkuCount());
                                orderQueryItemRsp.setTotalAmount(orderItemDto.getNowPrice().multiply(new BigDecimal(orderItemDto.getSkuCount())));
                                orderQueryItemRsp.setCurrencySymbol(symbol);
                                orderQueryItemRspList.add(orderQueryItemRsp);
                            }
                        }
                        // 支付链接
//                        String payUrl = apiUrlPrefix + "/" + siteCode + PaymentRequestConstant.PAYMENT_REDIRECT + "?orderNo=" + orderNo;
                        String payUrl = UrlUtil.join(apiUrlPrefix, siteCode, PaymentRequestConstant.PAYMENT_REDIRECT + "?orderNo=" + orderNo);
                        orderQueryRsp.setPayUrl(payUrl);
                        // 电子发票链接
                        String invoiceUrl = Optional.ofNullable(orderMainDto.getInvoice()).orElse(new OrderInvoiceDto()).getInvoiceUrl();
                        orderQueryRsp.setInvoiceUrl(invoiceUrl);
                        // 物流查询链接
                        String waybillNo = Optional.ofNullable(orderMainDto.getDelivery()).orElse(new OrderDeliveryDto()).getWaybillNo();
                        String deliveryUrl = apiUrlPrefix + "/" + siteCode + "?waybillNo=" + waybillNo;
                        orderQueryRsp.setDeliveryUrl(deliveryUrl);
                    }
                }
                long time3 = System.currentTimeMillis();
                logger.info("order query end2, userId:[{}], cost:[{}]ms.", userId, time3-time1);
                // 存放缓存
                if (!CollectionUtils.isEmpty(orderQueryRspList)) {
//                    cache.set(orderListKey, orderQueryRspList, StoreOrderConsts.ReidsCache.MINUTE_10);
                }
                return ok(orderQueryRspList);
            } else {
                return err(StoreOrderConsts.ErrorInfo.GET_ORDER_FAIL_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.GET_ORDER_FAIL_MSG));
            }
        } catch (Exception e) {
            logger.error("order query fail, userId:[{}], errMsg:[{}]", userId, ExceptionUtils.getStackTrace(e));
            return err(StoreOrderConsts.ErrorInfo.ORDER_QUERY_ERR_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.ORDER_QUERY_ERR_MSG));
        }
    }

    /**
     * 获取货币符号
     *
     * @param siteCode
     * @return
     */
    private String getCurrencySymbol(String siteCode) {
        String symbol = "";
        try {
            String redisKey = String.format(CacheConsts.SITE_CODE_CURRENCY, siteCode);
            CurrencyDto currencyDto = (CurrencyDto)cache.get(redisKey);
            if (currencyDto != null) {
                symbol = currencyDto.getSymbol();
            } else {
                ResultT<CurrencyDto> currencyResult = currencyService.getBySiteCode(siteCode);
                if (currencyResult.isSuccess()) {
                    symbol = currencyResult.getData().getSymbol();
                }
            }
        } catch (Exception e) {
            symbol = BasicMapping.currencySymbolMap.get(siteCode);
            logger.error("get currency symbol errMsg:[{}]", ExceptionUtils.getStackTrace(e));
        }
        return symbol;
    }

    /**
     * 取消订单
     * @return
     */
    @ApiOperation(value = "取消订单")
    @PostMapping("/order/cancel")
    public Result cancelOrder(@RequestBody CancelOrderReq cancelOrderReq) {
        logger.info("order cancel begin, cancelOrderReq:[{}].", cancelOrderReq);
        Long orderNo = cancelOrderReq.getOrderNo();
        long time1 = System.currentTimeMillis();
        try {
            OrderMainDto param = new OrderMainDto();
            param.setOrderNo(orderNo);
            param.setUserId(ColorOSUser.getCurrentId());
            param.setOrderStatus(OrderConsts.OrderStatus.CANCELED);
            param.setCancelType(OrderConsts.CancelType.USER_CANCEL);
            param.setCancelReason("user cancel");
            ResultT<Integer> result = orderService.cancelOrder(param);
            if (result.isSuccess()) {
                long time2 = System.currentTimeMillis();
                logger.info("order cancel end, orderNo:[{}], cost:[{}]ms.", orderNo, time2-time1);
                return ok();
            } else {
                return err(StoreOrderConsts.ErrorInfo.CANCEL_ORDER_FAIL_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.CANCEL_ORDER_FAIL_MSG));
            }
        } catch (Exception e) {
            logger.error("order cancel fail, orderNo:[{}], errMsg:[{}]", orderNo, ExceptionUtils.getStackTrace(e));
            return err(StoreOrderConsts.ErrorInfo.ORDER_CANCEL_ERR_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.ORDER_CANCEL_ERR_MSG));
        }
    }

    /**
     * 根据订单号获取
     * @return
     */
    @ApiOperation(value = "订单信息获取-支付完成")
    @GetMapping("/order/payed/items")
    public Result getPayedItems(@PathVariable String siteCode, Long orderNo) {
        try {
            Long userId = ColorOSUser.getCurrentId();
            ResultT<OrderMainDto> orderResult = orderService.getOrderInfoByOrderNo(orderNo);
            if (orderResult.isSuccess()) {
                OrderMainDto orderMainDto = orderResult.getData();
                OrderQueryRsp orderQueryRsp = new OrderQueryRsp();
                if (orderMainDto != null) {
                    if (!userId.equals(orderMainDto.getUserId())) {
                        return ok(null);
                    }
                    orderQueryRsp.setOrderNo(orderMainDto.getOrderNo());
                    orderQueryRsp.setOrderStatus(orderMainDto.getOrderStatus());
                    orderQueryRsp.setCreateTime(orderMainDto.getCreateTime());
                    List<OrderItemDto> itemsList = orderMainDto.getItems();
                    List<OrderQueryItemRsp> orderQueryItemRspList = new ArrayList<>();
                    orderQueryRsp.setItems(orderQueryItemRspList);
                    if (!CollectionUtils.isEmpty(itemsList)) {
                        for (OrderItemDto orderItemDto : itemsList) {
                            // 商品明细
                            OrderQueryItemRsp orderQueryItemRsp = new OrderQueryItemRsp();
                            orderQueryItemRsp.setImageUrl(orderItemDto.getImageUrl());
                            orderQueryItemRsp.setSkuId(orderItemDto.getSkuId());
                            orderQueryItemRsp.setSkuName(orderItemDto.getSkuName());
                            orderQueryItemRsp.setColor(orderItemDto.getColor());
                            orderQueryItemRsp.setSkuSpec(orderItemDto.getSkuSpec());
                            orderQueryItemRsp.setSkuCount(orderItemDto.getSkuCount());
                            orderQueryItemRsp.setTotalAmount(orderItemDto.getNowPrice().multiply(new BigDecimal(orderItemDto.getSkuCount())));
                            orderQueryItemRspList.add(orderQueryItemRsp);
                        }
                    }
                    // 支付链接
                    String payUrl = UrlUtil.join(apiUrlPrefix, siteCode, PaymentRequestConstant.PAYMENT_REDIRECT + "?orderNo=" + orderNo);
                    orderQueryRsp.setPayUrl(payUrl);
                } else {
                    return ok(null);
                }
                return ok(orderQueryRsp);
            } else {
                return err(StoreOrderConsts.ErrorInfo.GET_ORDER_PAYED_ITEMS_ERR_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.GET_ORDER_PAYED_ITEMS_ERR_MSG));
            }
        } catch (Exception e) {
            logger.error("get order payed items fail, orderNo:[{}], errMsg:[{}]", orderNo, ExceptionUtils.getStackTrace(e));
            return err(StoreOrderConsts.ErrorInfo.GET_ORDER_PAYED_ITEMS_ERR_CODE, getMsgByCode(StoreOrderConsts.ErrorInfo.GET_ORDER_PAYED_ITEMS_ERR_MSG));
        }
    }


    /************** 接口限制 *****************/
    private boolean  isLimited(Long userId) {
        int limit = 5;// 限制请求次数
        int sec = 5;// 时间段，秒
        String key = "order:query:limit:user:" + userId;
        Integer maxLimit = cacheInteger.get(key);
        if (maxLimit == null) {
            cacheInteger.set(key, 1, sec);  //set时一定要加过期时间
            return false;
        } else if (maxLimit < limit) {
            cacheInteger.set(key, maxLimit + 1, sec);
            return false;
        } else {
            return true;
        }
    }

}
