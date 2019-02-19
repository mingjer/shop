package net.realme.mall.oms.util;

import net.realme.mall.basics.dto.SiteEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.util
 *
 * @author 91000044
 * @date 2018/8/13 11:41
 */
@Component
public class PathManager {

    @Value("${cms.page.save.dir}")
    private String cmsPageSaveDir;

    @Value("${page.cdn.url.prefix}")
    private String pageCdnPrefix;

    @Value("${site.url.prefix}")
    private String sitePrefix;

    @Value("${amazon.s3.bucket.name}")
    private String s3BucketName;

    @Value("${s3.cdn.url.prefix}")
    private String s3CdnPrefix;

    @Value("${shopping.page.save.dir}")
    private String shoppingSavePageDir;

    @Value("${shopping.site.url.prefix}")
    private String shoppingSiteUrlPrefix;

    @Value("${shopping.err_page.save.dir}")
    private String shoppingErrPageSaveDir;

    @Value("${shopping.err_page.file.name}")
    private String shoppingErrPageFileName;

    public String getShoppingSavePageDir() {
        return shoppingSavePageDir;
    }

    public void setShoppingSavePageDir(String shoppingSavePageDir) {
        this.shoppingSavePageDir = shoppingSavePageDir;
    }

    public String getCmsPageSaveDir() {
        return cmsPageSaveDir;
    }

    public void setCmsPageSaveDir(String cmsPageSaveDir) {
        this.cmsPageSaveDir = cmsPageSaveDir;
    }

    public String getPageCdnPrefix() {
        return pageCdnPrefix;
    }

    public void setPageCdnPrefix(String pageCdnPrefix) {
        this.pageCdnPrefix = pageCdnPrefix;
    }

    public String getSitePrefix() {
        return sitePrefix;
    }

    public String getSitePrefix(String siteCode) {
        if (!SiteEnum.GLOBAL_SITE.getValue().equals(siteCode)) {
            return sitePrefix + "/" + siteCode;
        }
        return sitePrefix;
    }

    public void setSitePrefix(String sitePrefix) {
        this.sitePrefix = sitePrefix;
    }

    public String getS3Url(String path) {
        return String.format(s3CdnPrefix, s3BucketName) + "/" + path;
    }

    public String getShoppingSiteUrlPrefix() {
        return shoppingSiteUrlPrefix;
    }

    public String getShoppingSiteUrlPrefix(String siteCode) {
        if (!SiteEnum.GLOBAL_SITE.getValue().equals(siteCode)) {
            return shoppingSiteUrlPrefix + "/" + siteCode;
        }
        return shoppingSiteUrlPrefix;
    }

    public void setShoppingSiteUrlPrefix(String shoppingSiteUrlPrefix) {
        this.shoppingSiteUrlPrefix = shoppingSiteUrlPrefix;
    }

    public String getShoppingErrPageSaveDir() {
        return shoppingErrPageSaveDir;
    }

    public void setShoppingErrPageSaveDir(String shoppingErrPageSaveDir) {
        this.shoppingErrPageSaveDir = shoppingErrPageSaveDir;
    }

    public String getShoppingErrPageFileName() {
        return shoppingErrPageFileName;
    }

    public void setShoppingErrPageFileName(String shoppingErrPageFileName) {
        this.shoppingErrPageFileName = shoppingErrPageFileName;
    }
}
