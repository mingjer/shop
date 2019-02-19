package net.realme.mall.product.model;

import javax.persistence.Column;

/**
 * Created by 91000156 on 2018/9/8 16:50
 */
public class SkuDetailUrisView {

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "sku_id")
    private Integer skuId;

    @Column(name = "user_defined_url")
    private String userDefinedUrl;

    @Column(name = "color_id")
    private String colorId;

    @Column(name = "spec_id")
    private String specId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public String getUserDefinedUrl() {
        return userDefinedUrl;
    }

    public void setUserDefinedUrl(String userDefinedUrl) {
        this.userDefinedUrl = userDefinedUrl;
    }

    public String getColorId() {
        return colorId;
    }

    public void setColorId(String colorId) {
        this.colorId = colorId;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }
}
