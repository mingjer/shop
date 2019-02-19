package net.realme.mall.product.model;

import javax.persistence.*;

@Table(name = "product_attribute_value")
public class ProductAttributeValue {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 产品id
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 属性id
     */
    @Column(name = "attr_id")
    private Integer attrId;

    /**
     * 属性值
     */
    @Column(name = "attr_val")
    private String attrVal;

    /**
     * 属性排序序号
     */
    private Integer sequence;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Long createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Long updatedTime;

    /**
     * 状态：0正常、1已删除
     */
    private Byte status;

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

    /**
     * 获取产品id
     *
     * @return product_id - 产品id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置产品id
     *
     * @param productId 产品id
     */
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

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Long getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取更新时间
     *
     * @return updated_time - 更新时间
     */
    public Long getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 设置更新时间
     *
     * @param updatedTime 更新时间
     */
    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * 获取状态：0正常、1已删除
     *
     * @return status - 状态：0正常、1已删除
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态：0正常、1已删除
     *
     * @param status 状态：0正常、1已删除
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}