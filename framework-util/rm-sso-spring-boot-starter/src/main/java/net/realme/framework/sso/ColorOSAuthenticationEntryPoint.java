package net.realme.framework.sso;

import net.realme.framework.util.dto.ResultUtil;
import net.realme.framework.web.util.RequestHelper;
import net.realme.framework.web.util.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.config.security
 *
 * @author 91000044
 * @date 2018/8/8 11:23
 */
public class ColorOSAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private SsoConfigProperties ssoConfigProperties;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        if (RequestHelper.isAjax(request)) {
            ResponseHelper.jsonResponse(request, response, ResultUtil.unauthorized(ssoConfigProperties.getLoginUrl()));
        } else {
            String returnUrl = request.getRequestURL().toString();
            if (request.getQueryString() != null) {
                returnUrl = returnUrl + "?" + request.getQueryString();
            }
            response.sendRedirect(ssoConfigProperties.getLoginUrl() + "?callback=" + URLEncoder.encode(returnUrl, "UTF-8"));
        }
    }
}
