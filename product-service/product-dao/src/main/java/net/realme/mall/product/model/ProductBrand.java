package net.realme.mall.product.model;

import javax.persistence.*;

@Table(name = "product_brand")
public class ProductBrand {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 品牌编码
     */
    private String code;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌描述
     */
    private String description;

    /**
     * 品牌排序值
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
     * 品牌状态：0正常，1已删除 
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
     * 获取品牌编码
     *
     * @return code - 品牌编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置品牌编码
     *
     * @param code 品牌编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取品牌名称
     *
     * @return name - 品牌名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置品牌名称
     *
     * @param name 品牌名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取品牌描述
     *
     * @return description - 品牌描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置品牌描述
     *
     * @param description 品牌描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取品牌排序值
     *
     * @return sequence - 品牌排序值
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置品牌排序值
     *
     * @param sequence 品牌排序值
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
     * 获取品牌状态：0正常，1已删除 
     *
     * @return status - 品牌状态：0正常，1已删除 
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置品牌状态：0正常，1已删除 
     *
     * @param status 品牌状态：0正常，1已删除 
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}