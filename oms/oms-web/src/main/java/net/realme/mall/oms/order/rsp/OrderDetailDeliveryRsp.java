package net.realme.mall.oms.order.rsp;

import java.io.Serializable;

/**
 * OMS订单详情-物流方式
 */
public class OrderDetailDeliveryRsp implements Serializable {

    /**
     * 物流方
     */
    private String carrier;

    /**
     * 费用
     */
    private String fee;

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
