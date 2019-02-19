package net.realme.mall.oms.config.security;

import net.realme.mall.oms.admin.facade.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.config.security
 *
 * @author 91000044
 * @date 2018/8/13 17:45
 */
public class LoginAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private AdminUserService adminUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            Authentication auth = super.authenticate(authentication);
            adminUserService.loginSuccess(authentication.getName());
            return auth;
        } catch (BadCredentialsException e) {
            adminUserService.loginFail(authentication.getName());
            throw e;
        } catch (LockedException e) {
            throw e;
        }
    }
}
