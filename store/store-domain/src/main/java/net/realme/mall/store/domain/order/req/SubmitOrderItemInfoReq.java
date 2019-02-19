package net.realme.mall.store.domain.order.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品信息-提交
 *
 * @author 91000182
 * @date 2018/9/3
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubmitOrderItemInfoReq implements Serializable {

    private List<SubmitItemsReq> orderItemList;

    private int quantity;// 商品总数

    private BigDecimal totalAmount;// 商品总金额

    private BigDecimal deliveryFee;// 运费

    private BigDecimal payAmount;// 应付总额

    public List<SubmitItemsReq> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<SubmitItemsReq> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(BigDecimal deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
}
