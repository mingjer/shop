package net.realme.mall.oms.cms.model;

import javax.persistence.*;

@Table(name = "cms_page")
public class CmsPage {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 页面名称
     */
    private String name;

    /**
     * 页面简介
     */
    private String description;

    /**
     * 页面(URL)路径,前缀/
     */
    private String uri;

    /**
     * 页面状态 1 有效 0 无效
     */
    private Byte status;

    /**
     * 站点ID
     */
    @Column(name = "site_codes")
    private String siteCodes;

    /**
     * 页面部署发布到的域名，1：www,2:buy，默认发布到www域名下
     */
    @Column(name = "domain_type")
    private Byte domainType;

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
     * 页面内容
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
     * 获取页面名称
     *
     * @return name - 页面名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置页面名称
     *
     * @param name 页面名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取页面简介
     *
     * @return description - 页面简介
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置页面简介
     *
     * @param description 页面简介
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取页面(URL)路径,前缀/
     *
     * @return uri - 页面(URL)路径,前缀/
     */
    public String getUri() {
        return uri;
    }

    /**
     * 设置页面(URL)路径,前缀/
     *
     * @param uri 页面(URL)路径,前缀/
     */
    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
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
     * 获取页面部署发布到的域名，1：www,2:buy，默认发布到www域名下
     *
     * @return domain_type - 页面部署发布到的域名，1：www,2:buy，默认发布到www域名下
     */
    public Byte getDomainType() {
        return domainType;
    }

    /**
     * 设置页面部署发布到的域名，1：www,2:buy，默认发布到www域名下
     *
     * @param domainType 页面部署发布到的域名，1：www,2:buy，默认发布到www域名下
     */
    public void setDomainType(Byte domainType) {
        this.domainType = domainType;
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
     * 获取页面内容
     *
     * @return content - 页面内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置页面内容
     *
     * @param content 页面内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}