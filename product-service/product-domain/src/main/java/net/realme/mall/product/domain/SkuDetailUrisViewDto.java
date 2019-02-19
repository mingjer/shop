package net.realme.mall.product.domain;

import java.io.Serializable;

/**
 * Created by 91000156 on 2018/9/8 16:57
 */
public class SkuDetailUrisViewDto implements Serializable {

    private static final long serialVersionUID = 8685368738092040876L;

    private Integer productId;

    private Integer skuId;

    private String userDefinedUrl;

    private String colorId;

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
