package net.realme.mall.product.domain.response;

import java.io.Serializable;

/**
 * Created by 91000156 on 2018/8/30 14:17
 */
public class ProductAttributeValueResponse implements Serializable {


    private static final long serialVersionUID = 592725628396624467L;

    private Integer id;

    /**
     * 产品id
     */
    private Integer productId;

    /**
     * 属性id
     */
    private Integer attrId;

    /**
     * 属性值
     */
    private String attrVal;

    /**
     * 属性排序序号
     */
    private Integer sequence;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取属性id
     *
     * @return attr_id - 属性id
     */
    public Integer getAttrId() {
        return attrId;
    }

    /**
     * 设置属性id
     *
     * @param attrId 属性id
     */
    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    /**
     * 获取属性值
     *
     * @return attr_val - 属性值
     */
    public String getAttrVal() {
        return attrVal;
    }

    /**
     * 设置属性值
     *
     * @param attrVal 属性值
     */
    public void setAttrVal(String attrVal) {
        this.attrVal = attrVal == null ? null : attrVal.trim();
    }

    /**
     * 获取属性排序序号
     *
     * @return sequence - 属性排序序号
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置属性排序序号
     *
     * @param sequence 属性排序序号
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
