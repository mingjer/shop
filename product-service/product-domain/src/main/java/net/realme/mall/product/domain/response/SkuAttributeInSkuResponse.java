package net.realme.mall.product.domain.response;

import java.io.Serializable;

/**
 * Created by 91000156 on 2018/9/13 10:43
 */
public class SkuAttributeInSkuResponse implements Serializable {

    private static final long serialVersionUID = 6143284751780472369L;

    /**
     * 属性id
     */
    private String attrId;

    /**
     * 属性名称
     */
    private String attrName;

    /**
     * 属性值id
     */
    private String attrValId;

    /**
     * 属性值
     */
    private String attrVal;

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrValId() {
        return attrValId;
    }

    public void setAttrValId(String attrValId) {
        this.attrValId = attrValId;
    }

    public String getAttrVal() {
        return attrVal;
    }

    public void setAttrVal(String attrVal) {
        this.attrVal = attrVal;
    }
}
