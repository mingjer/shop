package net.realme.mall.product.model;

import javax.persistence.*;

@Table(name = "product_sku_attribute")
public class ProductSkuAttribute {
    /**
     * 自增长id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 产品id
     */
    @Column(name = "product_id")
    private Integer productId;

    /**
     * 产品sku id
     */
    @Column(name = "sku_id")
    private Integer skuId;

    /**
     * 属性id
     */
    @Column(name = "attr_id")
    private String attrId;

    /**
     * 属性值id
     */
    @Column(name = "attr_val_id")
    private String attrValId;

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
     * 状态：0-上架，1-已下架
     */
    @Column(name = "shelf_status")
    private Byte shelfStatus;

    /**
     * 状态：0可用，1不可用
     */
    private Byte status;

    /**
     * 获取自增长id
     *
     * @return id - 自增长id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增长id
     *
     * @param id 自增长id
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
     * 获取产品sku id
     *
     * @return sku_id - 产品sku id
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * 设置产品sku id
     *
     * @param skuId 产品sku id
     */
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    /**
     * 获取属性id
     *
     * @return attr_id - 属性id
     */
    public String getAttrId() {
        return attrId;
    }

    /**
     * 设置属性id
     *
     * @param attrId 属性id
     */
    public void setAttrId(String attrId) {
        this.attrId = attrId == null ? null : attrId.trim();
    }

    /**
     * 获取属性值id
     *
     * @return attr_val_id - 属性值id
     */
    public String getAttrValId() {
        return attrValId;
    }

    /**
     * 设置属性值id
     *
     * @param attrValId 属性值id
     */
    public void setAttrValId(String attrValId) {
        this.attrValId = attrValId == null ? null : attrValId.trim();
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
     * 获取状态：0-上架，1-已下架
     *
     * @return shelf_status - 状态：0-上架，1-已下架
     */
    public Byte getShelfStatus() {
        return shelfStatus;
    }

    /**
     * 设置状态：0-上架，1-已下架
     *
     * @param shelfStatus 状态：0-上架，1-已下架
     */
    public void setShelfStatus(Byte shelfStatus) {
        this.shelfStatus = shelfStatus;
    }

    /**
     * 获取状态：0可用，1不可用
     *
     * @return status - 状态：0可用，1不可用
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态：0可用，1不可用
     *
     * @param status 状态：0可用，1不可用
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}