package net.realme.mall.basics.dto;

import java.io.Serializable;

public class TranslationQuery implements Serializable {


    private Integer page;
    private Integer limit;
    private String t9nKey;
    private String t9nValue;
    private String orderBy;
    private String siteCode;


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

    public String getT9nValue() {
        return t9nValue;
    }

    public void setT9nValue(String t9nValue) {
        this.t9nValue = t9nValue;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getT9nKey() {
        return t9nKey;
    }

    public void setT9nKey(String t9nKey) {
        this.t9nKey = t9nKey;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }
}
