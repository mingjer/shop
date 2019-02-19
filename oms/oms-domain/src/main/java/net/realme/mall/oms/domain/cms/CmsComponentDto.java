package net.realme.mall.oms.domain.cms;

import java.io.Serializable;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.cms.domain
 *
 * @author 91000044
 * @date 2018/7/28 15:44
 */
public class CmsComponentDto implements Serializable {

    private static final long serialVersionUID = -2384475574657054078L;
    private Integer id;
    private String siteCodes;
    private Byte status;
    private Byte isDefault;
    private String type;
    private String name;
    private String content;
    private Integer ownerId;
    private String ownerName;
    private Long createdAt;
    private Long updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getSiteCodes() {
        return siteCodes;
    }

    public void setSiteCodes(String siteCodes) {
        this.siteCodes = siteCodes;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }
}
