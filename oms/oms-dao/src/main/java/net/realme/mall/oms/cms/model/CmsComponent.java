package net.realme.mall.oms.cms.model;

import javax.persistence.*;

@Table(name = "cms_component")
public class CmsComponent {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 组件类型
     */
    private String type;

    /**
     * 组件名称
     */
    private String name;

    /**
     * 页面状态 1 有效 0 无效
     */
    private Byte status;

    /**
     * 是否默认值
     */
    @Column(name = "is_default")
    private Byte isDefault;

    /**
     * 站点ID
     */
    @Column(name = "site_codes")
    private String siteCodes;

    /**
     * 创建者ID
     */
    @Column(name = "owner_id")
    private Integer ownerId;

    /**
     * 创建者名称
     */
    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    /**
     * 组件的值(内容)
     */
    private String content;

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
     * 获取组件类型
     *
     * @return type - 组件类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置组件类型
     *
     * @param type 组件类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取组件名称
     *
     * @return name - 组件名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置组件名称
     *
     * @param name 组件名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取页面状态 1 有效 0 无效
     *
     * @return status - 页面状态 1 有效 0 无效
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置页面状态 1 有效 0 无效
     *
     * @param status 页面状态 1 有效 0 无效
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取是否默认值
     *
     * @return is_default - 是否默认值
     */
    public Byte getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否默认值
     *
     * @param isDefault 是否默认值
     */
    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 获取站点ID
     *
     * @return site_codes - 站点ID
     */
    public String getSiteCodes() {
        return siteCodes;
    }

    /**
     * 设置站点ID
     *
     * @param siteCodes 站点ID
     */
    public void setSiteCodes(String siteCodes) {
        this.siteCodes = siteCodes == null ? null : siteCodes.trim();
    }

    /**
     * 获取创建者ID
     *
     * @return owner_id - 创建者ID
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * 设置创建者ID
     *
     * @param ownerId 创建者ID
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * 获取创建者名称
     *
     * @return owner_name - 创建者名称
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 设置创建者名称
     *
     * @param ownerName 创建者名称
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    /**
     * @return created_at
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt
     */
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return updated_at
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt
     */
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 获取组件的值(内容)
     *
     * @return content - 组件的值(内容)
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置组件的值(内容)
     *
     * @param content 组件的值(内容)
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}