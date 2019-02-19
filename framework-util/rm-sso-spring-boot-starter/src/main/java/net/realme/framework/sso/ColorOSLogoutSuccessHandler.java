package net.realme.framework.sso;

import net.realme.framework.util.dto.ResultUtil;
import net.realme.framework.web.util.RequestHelper;
import net.realme.framework.web.util.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.framework.sso
 *
 * @author 91000044
 * @date 2018/9/26 9:26
 */
public class ColorOSLogoutSuccessHandler implements LogoutSuccessHandler {

    @Autowired
    private SsoConfigProperties ssoConfigProperties;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        if (RequestHelper.isAjax(request)) {
//            ResponseHelper.jsonResponse(request, response, ResultUtil.success());
//        } else {
//            String returnUrl = request.getRequestURL().toString();
//            if (request.getQueryString() != null) {
//                returnUrl = returnUrl + "?" + request.getQueryString();
//            }
//            response.sendRedirect(ssoConfigProperties.getLogoutUrl() + "?callback=" + URLEncoder.encode(returnUrl, "UTF-8"));
//        }
    }

}
