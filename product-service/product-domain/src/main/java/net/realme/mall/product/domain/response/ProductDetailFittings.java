package net.realme.mall.product.domain.response;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by 91000156 on 2018/9/19 11:23
 */
public class ProductDetailFittings implements Serializable {

    private static final long serialVersionUID = 5866271670980885066L;

    /**
     * 配件sku id
     */
    private Integer partSkuId;

    /**
     * 商品spu id
     */
    private Integer productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * sku名称
     */
    private String skuName;

    /**
     *  货币符号
     */
    private String symbol;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 站点编码
     */
    private String siteCode;

    /**
     * 产品站概览url
     */
    private String itemSitesUrl;

    /**
     * 橱窗图按json存储
     */
    private String overviewUri;

    public Integer getPartSkuId() {
        return partSkuId;
    }

    public void setPartSkuId(Integer partSkuId) {
        this.partSkuId = partSkuId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getItemSitesUrl() {
        return itemSitesUrl;
    }

    public void setItemSitesUrl(String itemSitesUrl) {
        this.itemSitesUrl = itemSitesUrl;
    }

    public String getOverviewUri() {
        return overviewUri;
    }

    public void setOverviewUri(String overviewUri) {
        this.overviewUri = overviewUri;
    }
}
