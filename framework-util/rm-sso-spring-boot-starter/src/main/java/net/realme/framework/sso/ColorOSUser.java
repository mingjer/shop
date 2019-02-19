package net.realme.framework.sso;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.store.config.security
 *
 * @author 91000044
 * @date 2018/8/31 20:49
 */
public class ColorOSUser extends User implements Serializable {

    private static final long serialVersionUID = 7544447706312366182L;

    private String ssoid;
    /**
     * 等同于ssoid
     */
    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * OPPO账号
     */
    private String accountName;
    private String status;
    private String birthday;
    private String sex;
    private Map<String, String> avatar;

    /**
     * 获取当前登录用户
     * @return
     */
    public static ColorOSUser getCurrent() {
        return (ColorOSUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取当前登录用户SSOID
     * @return
     */
    public static long getCurrentId() {
        ColorOSUser user = getCurrent();
        return Long.valueOf(user.getSsoid());
    }

    public ColorOSUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public ColorOSUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public String getSsoid() {
        return ssoid;
    }

    public void setSsoid(String ssoid) {
        this.ssoid = ssoid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Map<String, String> getAvatar() {
        return avatar;
    }

    public void setAvatar(Map<String, String> avatar) {
        this.avatar = avatar;
    }
}
