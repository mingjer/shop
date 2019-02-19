package net.realme.framework.util.dto;

import java.io.Serializable;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.framework.util.dto
 *
 * @author 91000044
 * @date 2018/7/28 17:15
 */
public class PagedQuery implements Serializable {

    private static final long serialVersionUID = -8096358071132703013L;
    private Integer page;
    private Integer limit;
    private String orderBy;

    public PagedQuery(Integer page, Integer limit) {
        this.page = page;
        this.limit = limit;
    }

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

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
