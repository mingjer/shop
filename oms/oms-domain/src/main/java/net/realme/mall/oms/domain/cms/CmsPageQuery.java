package net.realme.mall.oms.domain.cms;

import java.io.Serializable;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.domain.cms
 *
 * @author 91000044
 * @date 2018/7/31 16:51
 */
public class CmsPageQuery implements Serializable {
    private static final long serialVersionUID = 619269070297780202L;

    private Integer page;
    private Integer limit;
    private String siteCode;
    private String orderBy;
    private String ownerId;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
