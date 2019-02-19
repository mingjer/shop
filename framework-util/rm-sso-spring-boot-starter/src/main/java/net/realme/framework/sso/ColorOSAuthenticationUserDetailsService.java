package net.realme.framework.sso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.store.config.security
 *
 * @author 91000044
 * @date 2018/8/31 15:02
 */
public class ColorOSAuthenticationUserDetailsService implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    @Autowired
    private ColorOSAuthClient colorOSAuthClient;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken authToken) throws UsernameNotFoundException {
        ColorOSAuthenticationPrincipal principal = (ColorOSAuthenticationPrincipal) authToken.getPrincipal();
        ColorOSAuthResponse authResult = colorOSAuthClient.sessionAuthDetail(principal.getSessionId(), principal.getImei(), principal.getClientIp());
        if (authResult == null || !authResult.isSuccess() || authResult.getData() == null) {
            throw new UsernameNotFoundException(authResult != null ? authResult.getError().getMessage() : "bad credential");
        }
        ColorOSAuthResponseData data = authResult.getData();
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        ColorOSUser user = new ColorOSUser(data.getUserName(), "", authorities);
        user.setSsoid(data.getSsoid());
        user.setUserId(data.getUserId());
        user.setUsername(data.getUserName());
        user.setAccountName(data.getAccountName());
        user.setStatus(data.getStatus());
        user.setBirthday(data.getBirthday());
        user.setSex(data.getSex());
        user.setAvatar(data.getAvatar());
        return user;
    }
}
