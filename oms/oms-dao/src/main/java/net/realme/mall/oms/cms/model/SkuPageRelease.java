package net.realme.mall.oms.cms.model;

import javax.persistence.*;

@Table(name = "sku_page_release")
public class SkuPageRelease {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * sku id
     */
    @Column(name = "sku_id")
    private Integer skuId;

    /**
     * 页面ID
     */
    @Column(name = "page_id")
    private Integer pageId;

    /**
     * 站点编码,与sku_id一对一
     */
    @Column(name = "site_code")
    private String siteCode;

    /**
     * 版本号，时间戳
     */
    private Long version;

    /**
     * 计划发布时间
     */
    @Column(name = "scheduled_at")
    private Long scheduledAt;

    /**
     * 实际发布时间
     */
    @Column(name = "released_at")
    private Long releasedAt;

    /**
     * 状态 0 发布中 1 已发布 2 发布失败
     */
    private Byte status;

    /**
     * 执行者ID
     */
    @Column(name = "operator_id")
    private Integer operatorId;

    /**
     * 操作者名称
     */
    @Column(name = "operator_name")
    private String operatorName;

    /**
     * 渲染后的完整页面
     */
    @Column(name = "rendered_html")
    private String renderedHtml;

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
     * 获取sku id
     *
     * @return sku_id - sku id
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * 设置sku id
     *
     * @param skuId sku id
     */
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    /**
     * 获取页面ID
     *
     * @return page_id - 页面ID
     */
    public Integer getPageId() {
        return pageId;
    }

    /**
     * 设置页面ID
     *
     * @param pageId 页面ID
     */
    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    /**
     * 获取站点编码,与sku_id一对一
     *
     * @return site_code - 站点编码,与sku_id一对一
     */
    public String getSiteCode() {
        return siteCode;
    }

    /**
     * 设置站点编码,与sku_id一对一
     *
     * @param siteCode 站点编码,与sku_id一对一
     */
    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode == null ? null : siteCode.trim();
    }

    /**
     * 获取版本号，时间戳
     *
     * @return version - 版本号，时间戳
     */
    public Long getVersion() {
        return version;
    }

    /**
     * 设置版本号，时间戳
     *
     * @param version 版本号，时间戳
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * 获取计划发布时间
     *
     * @return scheduled_at - 计划发布时间
     */
    public Long getScheduledAt() {
        return scheduledAt;
    }

    /**
     * 设置计划发布时间
     *
     * @param scheduledAt 计划发布时间
     */
    public void setScheduledAt(Long scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    /**
     * 获取实际发布时间
     *
     * @return released_at - 实际发布时间
     */
    public Long getReleasedAt() {
        return releasedAt;
    }

    /**
     * 设置实际发布时间
     *
     * @param releasedAt 实际发布时间
     */
    public void setReleasedAt(Long releasedAt) {
        this.releasedAt = releasedAt;
    }

    /**
     * 获取状态 0 发布中 1 已发布 2 发布失败
     *
     * @return status - 状态 0 发布中 1 已发布 2 发布失败
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置状态 0 发布中 1 已发布 2 发布失败
     *
     * @param status 状态 0 发布中 1 已发布 2 发布失败
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取执行者ID
     *
     * @return operator_id - 执行者ID
     */
    public Integer getOperatorId() {
        return operatorId;
    }

    /**
     * 设置执行者ID
     *
     * @param operatorId 执行者ID
     */
    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    /**
     * 获取操作者名称
     *
     * @return operator_name - 操作者名称
     */
    public String getOperatorName() {
        return operatorName;
    }

    /**
     * 设置操作者名称
     *
     * @param operatorName 操作者名称
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    /**
     * 获取渲染后的完整页面
     *
     * @return rendered_html - 渲染后的完整页面
     */
    public String getRenderedHtml() {
        return renderedHtml;
    }

    /**
     * 设置渲染后的完整页面
     *
     * @param renderedHtml 渲染后的完整页面
     */
    public void setRenderedHtml(String renderedHtml) {
        this.renderedHtml = renderedHtml == null ? null : renderedHtml.trim();
    }
}