package net.realme.mall.product.domain;

import java.io.Serializable;

/**
 * Created by 91000156 on 2018/9/7 19:16
 */
public class ProductAttributesViewDto implements Serializable {

    private static final long serialVersionUID = -3017405456004142487L;
    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 属性id
     */
    private String attrId;

    /**
     * 属性英文名
     */
    private String name;

    /**
     * 属性描述
     */
    private String description;

    /**
     * 属性值id
     */
    private String attrValId;

    /**
     * 属性值
     */
    private String attrVal;

    /**
     * 排序序号
     */
    private Integer sequence;

    /**
     * 此属性对应的Url
     */
    private String userDefinedUrl;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getAttrId() {
        return attrId;
    }

    public void setAttrId(String attrId) {
        this.attrId = attrId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getUserDefinedUrl() {
        return userDefinedUrl;
    }

    public void setUserDefinedUrl(String userDefinedUrl) {
        this.userDefinedUrl = userDefinedUrl;
    }
}
