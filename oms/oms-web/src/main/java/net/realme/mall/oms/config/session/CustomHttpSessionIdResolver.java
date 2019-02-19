package net.realme.mall.oms.config.session;

import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.config.security
 *
 * 同时支持cookie和header方式
 * @author 91000044
 * @date 2018/8/10 21:29
 */
public class CustomHttpSessionIdResolver implements HttpSessionIdResolver {

    private HeaderHttpSessionIdResolver httpSessionIdResolver = HeaderHttpSessionIdResolver.xAuthToken();
    private CookieHttpSessionIdResolver cookieHttpSessionIdResolver = new CookieHttpSessionIdResolver();

    @Override
    public List<String> resolveSessionIds(HttpServletRequest httpServletRequest) {
        List<String> ids = httpSessionIdResolver.resolveSessionIds(httpServletRequest);
        if (!ids.isEmpty()) {
            return ids;
        }
        return cookieHttpSessionIdResolver.resolveSessionIds(httpServletRequest);
    }

    @Override
    public void setSessionId(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s) {
        httpSessionIdResolver.setSessionId(httpServletRequest, httpServletResponse, s);
        cookieHttpSessionIdResolver.setSessionId(httpServletRequest, httpServletResponse, s);
    }

    @Override
    public void expireSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpSessionIdResolver.expireSession(httpServletRequest, httpServletResponse);
        cookieHttpSessionIdResolver.expireSession(httpServletRequest, httpServletResponse);
    }
}
