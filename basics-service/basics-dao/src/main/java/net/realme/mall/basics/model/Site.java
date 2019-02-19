package net.realme.mall.basics.model;


import javax.persistence.*;

@Table(name = "site")
public class Site {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    /**
     *
     * 站点唯一标识
     */
    @Column(name = "site_code")
    private String siteCode;
    /**
     *关联货币字段
     */
    @Column(name = "currency_abbr")
    private String currencyAbbr;
    /**
     * 国家名称
     */
    @Column(name = "country")
    private String country;
    /**
     * 国家编码（两位）
     */
    @Column(name = "country_code")
    private String countryCode;

    /**
    /**
     * 语言名称
     */
    @Column(name = "language")
    private String language;

    /**
     * 语言名称
     */
    @Column(name = "region")
    private String region;
    /**
     * 图标
     */
    @Column(name = "icon")
    private String icon;
    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Long createdAt;

    /**
     * 控制开关
     */
    @Column(name = "status")
    private  Byte status;
    /**
     * 更新时间
     */
    @Column(name = "updated_at")
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

    public String getCurrencyAbbr() {
        return currencyAbbr;
    }

    public void setCurrencyAbbr(String currencyAbbr) {
        this.currencyAbbr = currencyAbbr;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
