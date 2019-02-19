package net.realme.mall.product.model;

import javax.persistence.Column;

/**
 * Created by 91000156 on 2018/9/13 11:32
 */
public class AttrValJoinSku {

    @Column(name = "sku_id")
    private Integer skuId;

    @Column(name = "attr_id")
    private Integer attrId;

    @Column(name = "attr_name")
    private String attrName;

    @Column(name = "attr_val_id")
    private String attrValId;

    @Column(name = "attr_val")
    private String attrVal;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
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
