package net.realme.mall.oms.config.security;

import net.realme.framework.util.constant.CommonStatus;
import net.realme.mall.oms.domain.admin.AdminUserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.admin.impl
 *
 * @author 91000044
 * @date 2018/8/7 17:19
 */
public class AuthUserDetails extends User implements Serializable {

    private static final long serialVersionUID = -6598264235352272327L;

    private Integer id;
    private String nickname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public AuthUserDetails(AdminUserDto adminUserDto,
                           Collection<? extends GrantedAuthority> authorities) {
        super(adminUserDto.getName(),
                adminUserDto.getPassword(),
                CommonStatus.ENABLED == adminUserDto.getStatus(),
                true,
                true,
                adminUserDto.getLoginFailTimes() == null || adminUserDto.getLoginFailTimes() < 7
                        || (System.currentTimeMillis() - adminUserDto.getLastLoginAt()) > 1800000,
                authorities
        );
        this.id = adminUserDto.getId();
        this.nickname = adminUserDto.getNickname();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return super.getAuthorities();
    }

    /**
     * 获取当前登录用户
     * @return
     */
    public static AuthUserDetails getCurrent() {
        return (AuthUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
