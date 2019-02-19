package net.realme.framework.sso;

import net.realme.framework.util.text.RMTextUtil;
import net.realme.framework.web.util.RequestHelper;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.store.config.security
 *
 * @author 91000044
 * @date 2018/8/30 23:41
 */
public class ColorOSAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    private String SESSION_NAME = "sessionId";

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
        String sessionId = null;
        Cookie cookie = WebUtils.getCookie(httpServletRequest, SESSION_NAME);
        if (cookie == null) {
            return null;
        } else {
            sessionId = cookie.getValue();
        }
        return new ColorOSAuthenticationPrincipal(sessionId, null, RequestHelper.getIpAddress(httpServletRequest));
    }


    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
        return "";
    }
}
