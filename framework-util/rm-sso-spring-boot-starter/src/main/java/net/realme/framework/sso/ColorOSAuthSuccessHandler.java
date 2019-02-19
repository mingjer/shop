package net.realme.framework.sso;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
public class ColorOSAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ColorOSUser colorOSUser =  (ColorOSUser) authentication.getPrincipal();
        Cookie myCookie = new Cookie("nickname", URLEncoder.encode(colorOSUser.getUsername(), "UTF-8"));
        myCookie.setSecure(true);
        httpServletResponse.addCookie(myCookie);
    }
}
