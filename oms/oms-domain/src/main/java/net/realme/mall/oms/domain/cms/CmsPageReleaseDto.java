package net.realme.mall.oms.domain.cms;

import java.io.Serializable;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.cms.domain
 *
 * @author 91000044
 * @date 2018/7/28 15:47
 */
public class CmsPageReleaseDto implements Serializable {

    private static final long serialVersionUID = -8324473530828759456L;

    private Integer id;
    private Integer pageId;
    private Integer siteId;
    private Long version;
    private Long scheduledAt;
    private Long releasedAt;
    private Byte isReleased;

    private String renderedHtml;
    private Integer operatorId;
    private String operatorName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPageId() {
        return pageId;
    }

    public void setPageId(Integer pageId) {
        this.pageId = pageId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(Long scheduledAt) {
        this.scheduledAt = scheduledAt;
    }

    public Long getReleasedAt() {
        return releasedAt;
    }

    public void setReleasedAt(Long releasedAt) {
        this.releasedAt = releasedAt;
    }

    public Byte getIsReleased() {
        return isReleased;
    }

    public void setIsReleased(Byte isReleased) {
        this.isReleased = isReleased;
    }

    public String getRenderedHtml() {
        return renderedHtml;
    }

    public void setRenderedHtml(String renderedHtml) {
        this.renderedHtml = renderedHtml;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
