package net.realme.framework.sso;

import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultUtil;
import net.realme.framework.web.controller.BaseController;
import net.realme.framework.web.util.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.framework.sso
 *
 * @author 91000044
 * @date 2018/9/28 18:13
 */
@RestController
public class SsoAuthController extends BaseController {

    @Autowired
    private SsoConfigProperties ssoConfigProperties;

    @PostMapping("/login")
    public Result login(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
//            ColorOSUser colorOSUser = ColorOSUser.getCurrent();
            HashMap<String, String> data = new HashMap<>(10);
//            data.put("id", colorOSUser.getSsoid());
//            data.put("username", colorOSUser.getUsername());
            return ok(data);
        } else {
            return errI18N("err.auth");
        }
    }

    @GetMapping("/logout")
    public Result logout(HttpServletRequest request, HttpSession session) {
        try {
            logger.info("session {} logout", request.getSession().getId());
            SecurityContextHolder.clearContext();
            if(session != null) {
                session.invalidate();
            }
            for(Cookie cookie : request.getCookies()) {
                cookie.setMaxAge(0);
            }
            return ok();
        } catch (Exception e) {
            logger.error("logout failed", e);
        }
        return errI18N("err.logout");
    }
}
