package net.realme.mall.basics.dto;

import java.io.Serializable;

public class TranslationDto implements Serializable {

    private Integer id;
    private String siteCode;
    private String t9nKey;
    private String t9nValue;
    private byte status;
    private Long createdAt;
    private Long updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    public String getT9nKey() {
        return t9nKey;
    }

    public void setT9nKey(String t9nKey) {
        this.t9nKey = t9nKey;
    }

    public String getT9nValue() {
        return t9nValue;
    }

    public void setT9nValue(String t9nValue) {
        this.t9nValue = t9nValue;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
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
}
