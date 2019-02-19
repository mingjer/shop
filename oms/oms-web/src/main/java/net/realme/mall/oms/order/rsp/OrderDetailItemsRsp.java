package net.realme.mall.oms.order.rsp;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * OMS订单详情-商品信息
 */
public class OrderDetailItemsRsp implements Serializable {

    /**
     * 商品ID
     */
    private Integer skuId;
    /**
     * 商品数量
     */
    private Integer skuCount;

    private String skuName;
    /**
     * 物料代码
     */
    private String erpCode;

    /**
     * 现价
     */
    private BigDecimal nowPrice;

    private BigDecimal totalAmount;

    /**
     * IMEI
     */
    private String imei;

    /**
     * 货币符号
     */
    String currencySymbol;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getSkuCount() {
        return skuCount;
    }

    public void setSkuCount(Integer skuCount) {
        this.skuCount = skuCount;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getErpCode() {
        return erpCode;
    }

    public void setErpCode(String erpCode) {
        this.erpCode = erpCode;
    }

    public BigDecimal getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(BigDecimal nowPrice) {
        this.nowPrice = nowPrice;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }
}
