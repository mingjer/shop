package net.realme.mall.basics.model;

import javax.persistence.*;

@Table(name = "currency")
public class Currency {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 符号
     */
    private String symbol;

    /**
     * 缩写
     */
    private String abbr;

    /**
     * 名称
     */
    private String name;

    /**
     * 中文名称
     */
    @Column(name = "cn_name")
    private String cnName;

    /**
     * 1 正常 0 失效
     */
    private Byte status;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

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
     * 获取符号
     *
     * @return symbol - 符号
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * 设置符号
     *
     * @param symbol 符号
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    /**
     * 获取缩写
     *
     * @return abbr - 缩写
     */
    public String getAbbr() {
        return abbr;
    }

    /**
     * 设置缩写
     *
     * @param abbr 缩写
     */
    public void setAbbr(String abbr) {
        this.abbr = abbr == null ? null : abbr.trim();
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取中文名称
     *
     * @return cn_name - 中文名称
     */
    public String getCnName() {
        return cnName;
    }

    /**
     * 设置中文名称
     *
     * @param cnName 中文名称
     */
    public void setCnName(String cnName) {
        this.cnName = cnName == null ? null : cnName.trim();
    }

    /**
     * 获取1 正常 0 失效
     *
     * @return status - 1 正常 0 失效
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置1 正常 0 失效
     *
     * @param status 1 正常 0 失效
     */
    public void setStatus(Byte status) {
        this.status = status;
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
}