package net.realme.mall.basics.model;

import javax.persistence.*;



@Table(name="translation")
public class Translation {
    @Id
    private Integer id;

    /**
     * 关联货币字段
     */
    @Column(name = "site_code")
    private String siteCode;

    /**
     * 翻译字段
     */
    @Column(name = "t9n_key")
    private String t9nKey;

    /**
     * 翻译值
     */
    @Column(name = "t9n_value")
    private String t9nValue;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private long createdAt;
    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private long updatedAt;
    /**
     * 更新时间
     */
    @Column(name = "status")
    private String status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }
}
