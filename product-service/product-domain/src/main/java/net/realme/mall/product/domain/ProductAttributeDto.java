package net.realme.mall.product.domain;

import java.io.Serializable;

/**
 * Created by 91000156 on 2018/8/30 13:51
 */
public class ProductAttributeDto implements Serializable {

    private static final long serialVersionUID = 2829938745678981194L;

    /**
     * 属性ID
     */
    private Integer id;

    /**
     * 属性名
     */
    private String name;

    /**
     * 属性描述
     */
    private String description;

    /**
     * 属性排列序号
     */
    private Integer sequence;

    /**
     * 创建时间
     */
    private Long createdTime;

    /**
     * 更新时间
     */
    private Long updatedTime;

    /**
     * 状态：0可用，1不可用
     */
    private Byte status;

    /**
     * 获取属性名
     *
     * @return name - 属性名
     */
    public String getName() {
        return name;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 设置属性名
     *
     * @param name 属性名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取属性描述
     *
     * @return description - 属性描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置属性描述
     *
     * @param description 属性描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取属性排列序号
     *
     * @return sequence - 属性排列序号
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置属性排列序号
     *
     * @param sequence 属性排列序号
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

    @Override
    public String toString() {
        return "ProductAttributeDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", sequence=" + sequence +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                ", status=" + status +
                '}';
    }
}
