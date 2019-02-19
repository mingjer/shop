package net.realme.mall.store.domain.order.rsp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 我的订单查询结果
 */
public class OrderQueryRsp implements Serializable {
    private static final long serialVersionUID = 8260404872360743674L;
    private Long id;

    /**
     * 订单号
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderNo;

    /**
     * 11 Created, 21 Unpaid, 22 Paid, 31 Pushed,  32 Deliverying, 41 Finished, 42 Cancelled
     */
    private Integer orderStatus;

    /**
     * 创建时间
     */
//    @JsonFormat(timezone = "GMT+8", pattern = "yyyy.MM.dd HH:mm:ss")
//    private Date createTime;
    private Long createTime;

    /**
     * 支付倒计时
     */
    private Long countDown;

    // 商品明细
    List<OrderQueryItemRsp> items;

    // 支付链接
    private String payUrl;

    // 电子发票链接
    private String invoiceUrl;

    // 物流查询链接
    private String deliveryUrl;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public List<OrderQueryItemRsp> getItems() {
        return items;
    }

    public void setItems(List<OrderQueryItemRsp> items) {
        this.items = items;
    }

    public Long getCountDown() {
        return countDown;
    }

    public void setCountDown(Long countDown) {
        this.countDown = countDown;
    }

    public String getPayUrl() {
        return payUrl;
    }

    public void setPayUrl(String payUrl) {
        this.payUrl = payUrl;
    }

    public String getInvoiceUrl() {
        return invoiceUrl;
    }

    public void setInvoiceUrl(String invoiceUrl) {
        this.invoiceUrl = invoiceUrl;
    }

    public String getDeliveryUrl() {
        return deliveryUrl;
    }

    public void setDeliveryUrl(String deliveryUrl) {
        this.deliveryUrl = deliveryUrl;
    }
}