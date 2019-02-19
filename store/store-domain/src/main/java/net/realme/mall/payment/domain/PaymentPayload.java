package net.realme.mall.payment.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.store.domain.payment
 *
 * @author 91000044
 * @date 2018/9/1 23:25
 */
public class PaymentPayload implements Serializable {
    private static final long serialVersionUID = 1821897644409684788L;

    protected String siteCode;
    protected Long paymentNo;
    protected Long bizNo;
    protected Byte bizType;
    protected Long orderNo;
    protected BigDecimal amount;
    protected String payChannel;

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public Long getBizNo() {
        return bizNo;
    }

    public void setBizNo(Long bizNo) {
        this.bizNo = bizNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public Long getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(Long paymentNo) {
        this.paymentNo = paymentNo;
    }

    public Byte getBizType() {
        return bizType;
    }

    public void setBizType(Byte bizType) {
        this.bizType = bizType;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }
}
