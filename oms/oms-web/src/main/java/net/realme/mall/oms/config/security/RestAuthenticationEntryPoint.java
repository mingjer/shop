package net.realme.mall.oms.config.security;

import net.realme.framework.util.dto.ResultUtil;
import net.realme.framework.web.util.ResponseHelper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.config.security
 *
 * @author 91000044
 * @date 2018/8/8 11:23
 */
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResponseHelper.jsonResponse(request, response, ResultUtil.unauthorized(""));
    }
}
