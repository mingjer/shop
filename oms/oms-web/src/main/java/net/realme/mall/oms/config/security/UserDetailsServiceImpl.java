package net.realme.mall.oms.config.security;

import net.realme.mall.oms.admin.facade.AdminUserService;
import net.realme.mall.oms.domain.admin.AdminRoleDto;
import net.realme.mall.oms.domain.admin.AdminUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.admin.impl
 *
 * @author 91000044
 * @date 2018/8/7 12:36
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdminUserService adminUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminUserDto adminUser = adminUserService.getByName(username);
        if (adminUser == null) {
            throw new UsernameNotFoundException("admin user not found.");
        } else {
            Set<GrantedAuthority> authorities = new HashSet<>();
            //认证用户默认角色
            authorities.add(new SimpleGrantedAuthority(RoleCodeConstant.USER));
            if(adminUser.getRoles() != null && !adminUser.getRoles().isEmpty()){
                for(AdminRoleDto role : adminUser.getRoles()){
                    GrantedAuthority ga = new SimpleGrantedAuthority(role.getCode());
                    authorities.add(ga);
                }
            }
            return new AuthUserDetails(adminUser, authorities);
        }
    }
}

