package net.realme.framework.sso;

import java.io.Serializable;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.store.config.security
 *
 * @author 91000044
 * @date 2018/8/31 15:27
 */
public class ColorOSAuthenticationPrincipal implements Serializable {
    private static final long serialVersionUID = -5562280631727040226L;

    private String token;
    private String sessionId;
    private String clientIp= "";
    private String imei= "";

    public ColorOSAuthenticationPrincipal(String sessionId, String token, String clientIp) {
        this.sessionId = sessionId;
        this.token = token;
        this.clientIp = clientIp;
    }

    public ColorOSAuthenticationPrincipal(String sessionId, String token, String clientIp, String imei) {
        this(sessionId, token, clientIp);
        this.imei = imei;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
