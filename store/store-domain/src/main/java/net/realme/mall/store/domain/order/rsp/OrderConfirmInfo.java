package net.realme.mall.store.domain.order.rsp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 确认页面商品信息
 *
 * @author 91000182
 * @date 2018/9/3
 */
public class OrderConfirmInfo implements Serializable {

    private List<OrderConfirmItems> orderItemList;

    private int quantity;// 商品总数

    private BigDecimal totalAmount;// 商品总金额

    private String currencySymbol;// 货币符号

//    private BigDecimal deliveryFee;// 运费

//    private BigDecimal payAmount;// 应付总额


    public List<OrderConfirmItems> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderConfirmItems> orderItemList) {
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

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }
}
