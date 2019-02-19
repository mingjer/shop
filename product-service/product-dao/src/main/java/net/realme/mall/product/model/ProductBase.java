package net.realme.mall.product.model;

import javax.persistence.*;

@Table(name = "product_base")
public class ProductBase {
    /**
     * 自增长Id
     */
    @Id
    @Column(name = "product_id")
    @GeneratedValue(generator = "JDBC")
    private Integer productId;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 品牌编码
     */
    @Column(name = "brand_code")
    private String brandCode;

    /**
     * 商品类别编码
     */
    @Column(name = "category_code")
    private String categoryCode;

    /**
     * 1 实体产品 2 虚拟产品 3 套装
     */
    private Byte type;

    /**
     * 描述
     */
    private String description;

    /**
     * 排序值，越小排越靠前
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
     * 创建者
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 更新者
     */
    @Column(name = "updated_by")
    private Integer updatedBy;

    /**
     * 状态：0-上架，1-已下架
     */
    @Column(name = "shelf_status")
    private Byte shelfStatus;

    /**
     * 状态：0 正常，-1 已删除
     */
    private Byte status;

    /**
     * 额外字段JSON
     */
    @Column(name = "extra_params")
    private String extraParams;

    /**
     * 获取自增长Id
     *
     * @return product_id - 自增长Id
     */
    public Integer getProductId() {
        return productId;
    }

    /**
     * 设置自增长Id
     *
     * @param productId 自增长Id
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取产品名称
     *
     * @return name - 产品名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置产品名称
     *
     * @param name 产品名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取品牌编码
     *
     * @return brand_code - 品牌编码
     */
    public String getBrandCode() {
        return brandCode;
    }

    /**
     * 设置品牌编码
     *
     * @param brandCode 品牌编码
     */
    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode == null ? null : brandCode.trim();
    }

    /**
     * 获取商品类别编码
     *
     * @return category_code - 商品类别编码
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * 设置商品类别编码
     *
     * @param categoryCode 商品类别编码
     */
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    /**
     * 获取1 实体产品 2 虚拟产品 3 套装
     *
     * @return type - 1 实体产品 2 虚拟产品 3 套装
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置1 实体产品 2 虚拟产品 3 套装
     *
     * @param type 1 实体产品 2 虚拟产品 3 套装
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取排序值，越小排越靠前
     *
     * @return sequence - 排序值，越小排越靠前
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置排序值，越小排越靠前
     *
     * @param sequence 排序值，越小排越靠前
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
     * 获取创建者
     *
     * @return created_by - 创建者
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建者
     *
     * @param createdBy 创建者
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取更新者
     *
     * @return updated_by - 更新者
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置更新者
     *
     * @param updatedBy 更新者
     */
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
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
     * 获取状态：0 正常，-1 已删除
     *
     * @return status - 状态：0 正常，-1 已删除
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态：0 正常，-1 已删除
     *
     * @param status 状态：0 正常，-1 已删除
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取额外字段JSON
     *
     * @return extra_params - 额外字段JSON
     */
    public String getExtraParams() {
        return extraParams;
    }

    /**
     * 设置额外字段JSON
     *
     * @param extraParams 额外字段JSON
     */
    public void setExtraParams(String extraParams) {
        this.extraParams = extraParams == null ? null : extraParams.trim();
    }
}