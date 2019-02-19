package net.realme.mall.store.domain.order.req;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单取消
 *
 * @author 91000182
 * @date 2018/9/5
 */
public class CancelOrderReq implements Serializable {

    private Long orderNo;

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "CancelOrderReq [orderNo=" + orderNo + "]";
    }

}
