package net.realme.mall.product.model;

import javax.persistence.*;

@Table(name = "product_category")
public class ProductCategory {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 父级分类ID
     */
    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 分类层级 顶级0
     */
    private Byte level;

    /**
     * 分类编码
     */
    private String code;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类排序值
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
     * 状态：0正常，1不可用
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
     * 获取父级分类ID
     *
     * @return parent_id - 父级分类ID
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父级分类ID
     *
     * @param parentId 父级分类ID
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取分类层级 顶级0
     *
     * @return level - 分类层级 顶级0
     */
    public Byte getLevel() {
        return level;
    }

    /**
     * 设置分类层级 顶级0
     *
     * @param level 分类层级 顶级0
     */
    public void setLevel(Byte level) {
        this.level = level;
    }

    /**
     * 获取分类编码
     *
     * @return code - 分类编码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置分类编码
     *
     * @param code 分类编码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取分类名称
     *
     * @return name - 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置分类名称
     *
     * @param name 分类名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取分类排序值
     *
     * @return sequence - 分类排序值
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置分类排序值
     *
     * @param sequence 分类排序值
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
     * 获取状态：0正常，1不可用
     *
     * @return status - 状态：0正常，1不可用
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态：0正常，1不可用
     *
     * @param status 状态：0正常，1不可用
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}