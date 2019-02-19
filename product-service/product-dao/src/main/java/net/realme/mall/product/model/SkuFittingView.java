package net.realme.mall.product.model;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * Created by 91000156 on 2018/9/19 14:14
 */
public class SkuFittingView {

    @Column(name = "part_sku_id")
    private Integer partSkuId;

    @Column(name = "main_sku_id")
    private Integer mainSkuId;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "sku_name")
    private String skuName;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "site_code")
    private String siteCode;

    @Column(name = "item_sites_url")
    private String itemSitesUrl;

    @Column(name = "overview_uri")
    private String overviewUri;

    public Integer getPartSkuId() {
        return partSkuId;
    }

    public void setPartSkuId(Integer partSkuId) {
        this.partSkuId = partSkuId;
    }

    public Integer getMainSkuId() {
        return mainSkuId;
    }

    public void setMainSkuId(Integer mainSkuId) {
        this.mainSkuId = mainSkuId;
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
