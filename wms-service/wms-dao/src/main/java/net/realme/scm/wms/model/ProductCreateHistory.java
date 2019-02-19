package net.realme.scm.wms.model;

import javax.persistence.*;

@Table(name = "wms_product_create_history")
public class ProductCreateHistory {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 标记key
     */
    private String tag;

    /**
     * sku
     */
    private String sku;

    /**
     * skuName
     */
    @Column(name = "sku_name")
    private String skuName;

    /**
     * 物料号
     */
    private String number;

    /**
     * delhivery....
     */
    private String vendor;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Long createdAt;

    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private Long updatedAt;

    /**
     * 1:正常  0:失效
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
     * 获取标记key
     *
     * @return tag - 标记key
     */
    public String getTag() {
        return tag;
    }

    /**
     * 设置标记key
     *
     * @param tag 标记key
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * 获取sku
     *
     * @return sku - sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * 设置sku
     *
     * @param sku sku
     */
    public void setSku(String sku) {
        this.sku = sku == null ? null : sku.trim();
    }

    /**
     * 获取skuName
     *
     * @return sku_name - skuName
     */
    public String getSkuName() {
        return skuName;
    }

    /**
     * 设置skuName
     *
     * @param skuName skuName
     */
    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    /**
     * 获取物料号
     *
     * @return number - 物料号
     */
    public String getNumber() {
        return number;
    }

    /**
     * 设置物料号
     *
     * @param number 物料号
     */
    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    /**
     * 获取delhivery....
     *
     * @return vendor - delhivery....
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * 设置delhivery....
     *
     * @param vendor delhivery....
     */
    public void setVendor(String vendor) {
        this.vendor = vendor == null ? null : vendor.trim();
    }

    /**
     * 获取创建时间
     *
     * @return created_at - 创建时间
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置创建时间
     *
     * @param createdAt 创建时间
     */
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取更新时间
     *
     * @return updated_at - 更新时间
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 设置更新时间
     *
     * @param updatedAt 更新时间
     */
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 获取1:正常  0:失效
     *
     * @return status - 1:正常  0:失效
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置1:正常  0:失效
     *
     * @param status 1:正常  0:失效
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}