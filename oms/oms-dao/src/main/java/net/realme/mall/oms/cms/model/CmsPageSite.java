package net.realme.mall.oms.cms.model;

import javax.persistence.*;

@Table(name = "cms_page_site")
public class CmsPageSite {
    /**
     * 页面ID
     */
    @Column(name = "page_id")
    private Integer pageId;

    /**
     * 站点ID
     */
    @Column(name = "site_code")
    private String siteCode;

    /**
     * 页面路径
     */
    private String uri;

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
     * 获取站点ID
     *
     * @return site_code - 站点ID
     */
    public String getSiteCode() {
        return siteCode;
    }

    /**
     * 设置站点ID
     *
     * @param siteCode 站点ID
     */
    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode == null ? null : siteCode.trim();
    }

    /**
     * 获取页面路径
     *
     * @return uri - 页面路径
     */
    public String getUri() {
        return uri;
    }

    /**
     * 设置页面路径
     *
     * @param uri 页面路径
     */
    public void setUri(String uri) {
        this.uri = uri == null ? null : uri.trim();
    }
}