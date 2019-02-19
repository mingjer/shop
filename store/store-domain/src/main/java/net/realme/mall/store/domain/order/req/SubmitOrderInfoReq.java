package net.realme.mall.store.domain.order.req;

import java.io.Serializable;

/**
 * 订购订单信息--提交时用
 *
 * @author 91000182
 * @date 2018/9/5
 */
public class SubmitOrderInfoReq implements Serializable {

    private SubmitOrderAddressReq address;

    private SubmitOrderItemInfoReq orderConfirmInfo;

    public SubmitOrderAddressReq getAddress() {
        return address;
    }

    public void setAddress(SubmitOrderAddressReq address) {
        this.address = address;
    }

    public SubmitOrderItemInfoReq getOrderConfirmInfo() {
        return orderConfirmInfo;
    }

    public void setOrderConfirmInfo(SubmitOrderItemInfoReq orderConfirmInfo) {
        this.orderConfirmInfo = orderConfirmInfo;
    }
}
