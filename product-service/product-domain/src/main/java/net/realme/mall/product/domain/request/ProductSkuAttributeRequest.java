package net.realme.mall.product.domain.request;

import java.io.Serializable;

/**
 * Created by 91000156 on 2018/8/30 16:01
 */
public class ProductSkuAttributeRequest implements Serializable {


    private static final long serialVersionUID = -594278086952244094L;

    /**
     * 属性id
     */
    private String attrId;

    /**
     * 属性值id
     */
    private String attrValId;

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

    @Override
    public String toString() {
        return "ProductSkuAttributeRequest{" +
                "attrId='" + attrId + '\'' +
                ", attrValId='" + attrValId + '\'' +
                '}';
    }
}
