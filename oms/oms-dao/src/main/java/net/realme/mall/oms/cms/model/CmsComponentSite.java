package net.realme.mall.oms.cms.model;

import javax.persistence.*;

@Table(name = "cms_component_site")
public class CmsComponentSite {
    /**
     * 组件ID
     */
    @Column(name = "component_id")
    private Integer componentId;

    /**
     * 组件类型
     */
    @Column(name = "component_type")
    private String componentType;

    /**
     * 组件名称
     */
    @Column(name = "component_name")
    private String componentName;

    /**
     * 站点ID
     */
    @Column(name = "site_code")
    private String siteCode;

    /**
     * 获取组件ID
     *
     * @return component_id - 组件ID
     */
    public Integer getComponentId() {
        return componentId;
    }

    /**
     * 设置组件ID
     *
     * @param componentId 组件ID
     */
    public void setComponentId(Integer componentId) {
        this.componentId = componentId;
    }

    /**
     * 获取组件类型
     *
     * @return component_type - 组件类型
     */
    public String getComponentType() {
        return componentType;
    }

    /**
     * 设置组件类型
     *
     * @param componentType 组件类型
     */
    public void setComponentType(String componentType) {
        this.componentType = componentType == null ? null : componentType.trim();
    }

    /**
     * 获取组件名称
     *
     * @return component_name - 组件名称
     */
    public String getComponentName() {
        return componentName;
    }

    /**
     * 设置组件名称
     *
     * @param componentName 组件名称
     */
    public void setComponentName(String componentName) {
        this.componentName = componentName == null ? null : componentName.trim();
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
}