package net.realme.mall.oms.domain.cms;

import java.io.Serializable;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.cms.domain
 *
 * @author 91000044
 * @date 2018/7/28 15:46
 */
public class CmsPageDto implements Serializable {
    private static final long serialVersionUID = 41357245591685099L;

    private Integer id;
    private String name;
    private String description;
    private String siteCodes;
    private String[] siteCodesArr;
    private String content;
    private Byte status;
    private String uri;
    private Integer ownerId;
    private String ownerName;
    private Long createdAt;
    private Long updatedAt;
    private Byte domainType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getSiteCodes() {
        return siteCodes;
    }

    public void setSiteCodes(String siteCodes) {
        this.siteCodes = siteCodes;
        if (this.siteCodes != null && !"".equals(this.siteCodes)) {
            this.siteCodesArr = siteCodes.split(",");
        }
    }

    public String[] getSiteCodesArr() {
        return siteCodesArr;
    }

    public void setSiteCodesArr(String[] siteCodesArr) {
        this.siteCodesArr = siteCodesArr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getDomainType() {
        return domainType;
    }

    public void setDomainType(Byte domainType) {
        this.domainType = domainType;
    }
}
