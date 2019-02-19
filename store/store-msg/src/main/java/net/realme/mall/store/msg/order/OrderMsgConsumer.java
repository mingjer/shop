package net.realme.mall.store.msg.order;

import com.alibaba.fastjson.JSON;
import net.realme.framework.cache.redis.RedisCache;
import net.realme.mall.order.consts.OrderConsts;
import net.realme.mall.order.domain.OrderItemDto;
import net.realme.mall.order.domain.OrderMainDto;
import net.realme.mall.order.facade.OrderService;
import net.realme.mall.store.common.consts.StoreOrderConsts;
import net.realme.mall.store.domain.order.BuyOrderSkuInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderMsgConsumer {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisCache<List<BuyOrderSkuInfo>> cacheBuy;

    @Autowired
    private RedisCache<Integer> cacheSkuLogicInv;

    @Autowired
    private RedisCache<Integer> cacheOrderCreated;


    private void consumeOderMsg() {
        String orderJson = "";
        // 调用订单微服务保存到数据库
        OrderMainDto orderDto = JSON.parseObject(orderJson, OrderMainDto.class);
        orderService.addOrder(orderDto);
        // 删除商品的redis缓存
        String cacheOrderSkuKey = OrderConsts.CACHE_ORDER_SKU + orderDto.getUserId();
        cacheBuy.delete(cacheOrderSkuKey);
        // 扣减商品逻辑库存
        List<OrderItemDto> items = orderDto.getItems();
        for (OrderItemDto item : items) {
            String invKey = String.format(OrderConsts.CACHE_SKU_LOGIC_INVENTORY, item.getSkuId());
//            cacheSkuLogicInv.;
        }
        // 设置订单生成redis信息
        String invKey = String.format(OrderConsts.CACHE_ORDER_REATED, orderDto.getOrderNo());
        cacheOrderCreated.set(invKey, 1);



    }
}
