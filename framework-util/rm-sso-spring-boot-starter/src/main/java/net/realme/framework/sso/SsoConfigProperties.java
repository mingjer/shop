package net.realme.framework.sso;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.framework.rest.client
 *
 * @author 91000044
 * @date 2018/8/17 16:39
 */
@ConfigurationProperties(prefix = "coloros.auth")
public class SsoConfigProperties {

    private String appId;
    private String appKey;
    private String appSecret;
    private String appPackage;
    private String host;
    private String loginUrl;
    private String logoutUrl;
    private String[] ignoreList;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public String[] getIgnoreList() {
        return ignoreList;
    }

    public void setIgnoreList(String[] ignoreList) {
        this.ignoreList = ignoreList;
    }
}
