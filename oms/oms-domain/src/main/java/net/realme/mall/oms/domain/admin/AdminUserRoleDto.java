package net.realme.mall.oms.domain.admin;

import java.io.Serializable;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.domain.admin
 *
 * @author 91000044
 * @date 2018/8/4 17:06
 */
public class AdminUserRoleDto implements Serializable {

    private static final long serialVersionUID = -209268509190515032L;
    private Integer adminUserId;
    private String adminRoleCode;

    private Long createdAt;

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getAdminRoleCode() {
        return adminRoleCode;
    }

    public void setAdminRoleCode(String adminRoleCode) {
        this.adminRoleCode = adminRoleCode;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
