package net.realme.mall.product.domain.response;

import java.io.Serializable;

/**
 * Created by 91000156 on 2018/8/30 15:24
 */
public class ProductSkuAttributeResponse implements Serializable {

    private static final long serialVersionUID = -3496735685990564248L;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 产品sku id
     */
    private Integer skuId;

    /**
     * 属性id
     */
    private String attrId;

    /**
     * 属性值id
     */
    private String attrValId;

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

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public String getAttrValId() {
        return attrValId;
    }

    public void setAttrValId(String attrValId) {
        this.attrValId = attrValId;
    }
}
