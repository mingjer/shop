package net.realme.mall.oms.config.security;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.config.security
 *
 * @author 91000044
 * @date 2018/8/10 14:25
 */
public class RbacFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private static final Map<String,String> URL_ROLE_MAP;
    static {
        URL_ROLE_MAP = new LinkedHashMap<>();
        URL_ROLE_MAP.put("/login", RoleCodeConstant.ANONYMOUS);
        URL_ROLE_MAP.put("/logout", RoleCodeConstant.ANONYMOUS);
        URL_ROLE_MAP.put("/cms/page/preview**", RoleCodeConstant.ANONYMOUS);
        URL_ROLE_MAP.put("/**", RoleCodeConstant.USER);
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) o;
        String url = fi.getRequestUrl();
        for(Map.Entry<String,String> entry: URL_ROLE_MAP.entrySet()){
            if(antPathMatcher.match(entry.getKey(),url)){
                return SecurityConfig.createList(entry.getValue());
            }
        }
        return SecurityConfig.createList(RoleCodeConstant.USER);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
