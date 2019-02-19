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
 * @date 2018/9/3 18:52
 */
public class PaymentResponse implements Serializable {
    private static final long serialVersionUID = -7412482720813970566L;

    protected String siteCode;
    protected Long paymentNo;
    protected Long bizNo;
    protected Long orderNo;
    protected String payChannel;
    protected String payMethod;
    protected String paymentAccountId;
    protected Long bizType;
    protected String currencyCode;
    protected String txnNo;
    protected BigDecimal txnAmount;
    protected String txnType;
    protected Long txnTime;
    protected Byte status;
    protected String bankId;
    protected String bankTxnNo;
    protected String bankMerchantId;
    protected String errCode;
    protected String errMsg;
    protected String rawText;

    public Long getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(Long paymentNo) {
        this.paymentNo = paymentNo;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getPaymentAccountId() {
        return paymentAccountId;
    }

    public void setPaymentAccountId(String paymentAccountId) {
        this.paymentAccountId = paymentAccountId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getTxnNo() {
        return txnNo;
    }

    public void setTxnNo(String txnNo) {
        this.txnNo = txnNo;
    }

    public String getTxnType() {
        return txnType;
    }

    public void setTxnType(String txnType) {
        this.txnType = txnType;
    }

    public Long getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(Long txnTime) {
        this.txnTime = txnTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public BigDecimal getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(BigDecimal txnAmount) {
        this.txnAmount = txnAmount;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankTxnNo() {
        return bankTxnNo;
    }

    public void setBankTxnNo(String bankTxnNo) {
        this.bankTxnNo = bankTxnNo;
    }

    public String getBankMerchantId() {
        return bankMerchantId;
    }

    public void setBankMerchantId(String bankMerchantId) {
        this.bankMerchantId = bankMerchantId;
    }

    public String getRawText() {
        return rawText;
    }

    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

    public String getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(String payChannel) {
        this.payChannel = payChannel;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public Long getBizNo() {
        return bizNo;
    }

    public void setBizNo(Long bizNo) {
        this.bizNo = bizNo;
    }

    public Long getBizType() {
        return bizType;
    }

    public void setBizType(Long bizType) {
        this.bizType = bizType;
    }

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "rawText='" + rawText + '\'' +
                '}';
    }
}
