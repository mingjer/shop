package net.realme.mall.store.domain.order.rsp;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 我的订单查询结果
 */
public class OrderQueryItemRsp implements Serializable {
    private static final long serialVersionUID = 5675424463569589368L;

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
     * 图片地址
     */
    private String imageUrl;

    /**
     * 颜色
     */
    private String color;

    /**
     * 存储规格
     */
    private String skuSpec;

    /**
     * 总价
     */
    private BigDecimal totalAmount;

    /**
     * 货币符号
     */
    private String currencySymbol;

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSkuSpec() {
        return skuSpec;
    }

    public void setSkuSpec(String skuSpec) {
        this.skuSpec = skuSpec;
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