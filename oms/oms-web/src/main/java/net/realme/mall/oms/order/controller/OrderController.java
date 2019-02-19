package net.realme.mall.oms.order.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.framework.web.controller.BaseController;
import net.realme.mall.oms.order.req.OrderListQueryReq;
import net.realme.mall.oms.order.rsp.*;
import net.realme.mall.order.consts.OrderConsts;
import net.realme.mall.order.domain.OrderDeliveryDto;
import net.realme.mall.order.domain.OrderItemDto;
import net.realme.mall.order.domain.OrderMainDto;
import net.realme.mall.order.facade.OrderService;
import net.realme.mall.order.req.OmsOrderQueryReq;
import net.realme.mall.order.rsp.OmsOrderQueryRsp;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 91000182 on 2018/9/22
 */
@Api(tags = {"订单管理"})
@Validated
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;


    @ApiOperation(value = "获取订单列表信息")
    @GetMapping("/list")
    public Result getOrderList(OrderListQueryReq orderListQueryReq){
        logger.info("get order list param:[{}]", orderListQueryReq);
        OmsOrderQueryReq query = new OmsOrderQueryReq();
        BeanUtils.copyProperties(orderListQueryReq, query);
        ResultT<ResultList<OmsOrderQueryRsp>> list = orderService.getOmsOrderList(query);
        if (list.isSuccess()) {
            return ok(list.getData());
        }
        return err(list.getMsg());
    }

    @ApiOperation(value = "获取订单详情信息")
    @GetMapping("/detail")
    public Result getOrderDetail(Long orderNo){
        logger.info("get order detail orderNo:[{}]", orderNo);
        if (orderNo == null) {
            return err("orderNo cannot be null.");
        }
        try {
            OrderDetailRsp orderDetailRsp = new OrderDetailRsp();
            ResultT<OrderMainDto> orderResult = orderService.getOrderInfoByOrderNo(orderNo);
            if (orderResult.isSuccess()) {
                // 订单详情
                OrderMainDto orderMainDto = orderResult.getData();
                if (null != orderMainDto) {
                    orderDetailRsp.setOrderNo(orderMainDto.getOrderNo());
                    orderDetailRsp.setOrderStatus(orderMainDto.getOrderStatus());
                    orderDetailRsp.setCountry(orderMainDto.getCountry());
                    orderDetailRsp.setOrderTotalAmount(orderMainDto.getOrderTotalAmount());
                    orderDetailRsp.setCurrencySymbol(orderMainDto.getCurrencySymbol());
                    orderDetailRsp.setPaidTime(orderMainDto.getPaidTime());

                    // 收货信息
                    OrderDeliveryDto delivery = orderMainDto.getDelivery();
                    OrderDetailConsigneeRsp consignee = new OrderDetailConsigneeRsp();
                    BeanUtils.copyProperties(delivery, consignee);
                    orderDetailRsp.setConsignee(consignee);

                    // 用户信息
                    OrderDetailUserRsp userInfo = new OrderDetailUserRsp();
                    userInfo.setUserId(orderMainDto.getUserId());
                    userInfo.setNickName(orderMainDto.getUserName());
                    if (delivery != null) {
                        userInfo.setUserName(delivery.getFullName());
                        userInfo.setMail(delivery.getEmail());
                        userInfo.setMobile(delivery.getPhone());
                    }
                    orderDetailRsp.setUserInfo(userInfo);

                    // 商品信息
                    List<OrderDetailItemsRsp> items = new ArrayList<>();
                    List<OrderItemDto> orderItemDtos = orderMainDto.getItems();
                    if (!CollectionUtils.isEmpty(orderItemDtos)) {
                        for (OrderItemDto orderItemDto : orderItemDtos) {
                            OrderDetailItemsRsp orderDetailItemsRsp = new OrderDetailItemsRsp();
                            BeanUtils.copyProperties(orderItemDto, orderDetailItemsRsp);
                            orderDetailItemsRsp.setTotalAmount(orderItemDto.getNowPrice().multiply(new BigDecimal(orderItemDto.getSkuCount())));
                            orderDetailItemsRsp.setCurrencySymbol(orderMainDto.getCurrencySymbol());
                            items.add(orderDetailItemsRsp);
                        }
                        orderDetailRsp.setItems(items);
                    }

                    // 订单流水
                    List<OrderDetailStatusRsp> statusList = new ArrayList<>();
                    if (OrderConsts.OrderStatus.UNPAID == orderMainDto.getOrderStatus()) {
                        // 未支付
                        setOrderStatus(statusList, OrderConsts.OrderStatus.UNPAID, orderMainDto.getCreateTime());
                    } else if (OrderConsts.OrderStatus.PAID == orderMainDto.getOrderStatus()) {
                        // 未支付
                        setOrderStatus(statusList, OrderConsts.OrderStatus.UNPAID, orderMainDto.getCreateTime());
                        // 支付
                        setOrderStatus(statusList, OrderConsts.OrderStatus.PAID, orderMainDto.getPaidTime());
                    } else if (OrderConsts.OrderStatus.DELIVERYING == orderMainDto.getOrderStatus()) {
                        // 未支付
                        setOrderStatus(statusList, OrderConsts.OrderStatus.UNPAID, orderMainDto.getCreateTime());
                        // 支付
                        setOrderStatus(statusList, OrderConsts.OrderStatus.PAID, orderMainDto.getPaidTime());
                        // 发货中
                        setOrderStatus(statusList, OrderConsts.OrderStatus.DELIVERYING, orderMainDto.getShippingTime());
                    } else if (OrderConsts.OrderStatus.FINISHED == orderMainDto.getOrderStatus()) {
                        // 未支付
                        setOrderStatus(statusList, OrderConsts.OrderStatus.UNPAID, orderMainDto.getCreateTime());
                        // 支付
                        setOrderStatus(statusList, OrderConsts.OrderStatus.PAID, orderMainDto.getPaidTime());
                        // 发货中
                        setOrderStatus(statusList, OrderConsts.OrderStatus.DELIVERYING, orderMainDto.getShippingTime());
                        // 已完成
                        setOrderStatus(statusList, OrderConsts.OrderStatus.FINISHED, orderMainDto.getFinishedTime());
                    } else if (OrderConsts.OrderStatus.CANCELED == orderMainDto.getOrderStatus()) {
                        // 未支付
                        setOrderStatus(statusList, OrderConsts.OrderStatus.UNPAID, orderMainDto.getCreateTime());
                        // 取消
                        setOrderStatus(statusList, OrderConsts.OrderStatus.CANCELED, orderMainDto.getCancelledTime());
                    }
                    orderDetailRsp.setStatusList(statusList);

                    // 发票信息
                    OrderDetailInvoiceRsp invoice = new OrderDetailInvoiceRsp();
                    invoice.setInvoiceUrl(orderMainDto.getInvoice()==null?"":orderMainDto.getInvoice().getInvoiceUrl());
                    orderDetailRsp.setInvoice(invoice);

                    return ok(orderDetailRsp);
                } else {
                    return ok(null);
                }
            } else {
                return err(orderResult.getMsg());
            }
        } catch (Exception e) {
            logger.error("get order detail error, orderNo:[{}], errMsg:[{}]", orderNo, ExceptionUtils.getStackTrace(e));
            return err(ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     * 设置订单状态流水信息
     *
     * @param statusList
     * @param status
     * @param time
     */
    private void setOrderStatus(List<OrderDetailStatusRsp> statusList, int status, Long time) {
        OrderDetailStatusRsp statusRsp = new OrderDetailStatusRsp();
        statusRsp.setOrderStatus(status);
        try {
            statusRsp.setTime(new Date(time));
        } catch (Exception e) {
            logger.error("time err:[{}]", e);
        }
        statusList.add(statusRsp);
    }

}
