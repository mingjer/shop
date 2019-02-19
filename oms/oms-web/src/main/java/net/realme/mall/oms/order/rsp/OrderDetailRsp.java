package net.realme.mall.oms.order.rsp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * OMS订单详情
 */
public class OrderDetailRsp implements Serializable {

    /**
     * 订单号
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderNo;

    private Integer orderStatus;
    /**
     * 订单总金额
     */
    private BigDecimal orderTotalAmount;

    /**
     * 货币符号
     */
    private String currencySymbol;

    /**
     * 国家
     */
    private String country;
    /**
     * 付款时间
     */
    private Long paidTime;

    // 用户信息
    private OrderDetailUserRsp userInfo;

    // 收货信息
    private OrderDetailConsigneeRsp consignee;

    // 商品信息
    private List<OrderDetailItemsRsp> items;

    // 订单状态流水
    private List<OrderDetailStatusRsp> statusList;

    // 发票信息
    private OrderDetailInvoiceRsp invoice;

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(BigDecimal orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getPaidTime() {
        return paidTime;
    }

    public void setPaidTime(Long paidTime) {
        this.paidTime = paidTime;
    }

    public OrderDetailUserRsp getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(OrderDetailUserRsp userInfo) {
        this.userInfo = userInfo;
    }

    public OrderDetailConsigneeRsp getConsignee() {
        return consignee;
    }

    public void setConsignee(OrderDetailConsigneeRsp consignee) {
        this.consignee = consignee;
    }

    public List<OrderDetailItemsRsp> getItems() {
        return items;
    }

    public void setItems(List<OrderDetailItemsRsp> items) {
        this.items = items;
    }

    public OrderDetailInvoiceRsp getInvoice() {
        return invoice;
    }

    public void setInvoice(OrderDetailInvoiceRsp invoice) {
        this.invoice = invoice;
    }

    public List<OrderDetailStatusRsp> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<OrderDetailStatusRsp> statusList) {
        this.statusList = statusList;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }
}
