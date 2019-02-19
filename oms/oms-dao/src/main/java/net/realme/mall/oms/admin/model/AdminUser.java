package net.realme.mall.oms.admin.model;

import javax.persistence.*;
import java.util.List;

@Table(name = "admin_user")
public class AdminUser {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String nickname;

    /**
     * 管理员名称
     */
    private String name;

    private String email;

    /**
     * 管理员手机
     */
    private String phone;

    /**
     * 管理员手机哈希值
     */
    @Column(name = "phone_hash")
    private String phoneHash;

    /**
     * 密码
     */
    private String password;

    private Byte status;

    /**
     * 管理员备注
     */
    private String remark;

    /**
     * 上次成功登录时间
     */
    @Column(name = "last_login_at")
    private Long lastLoginAt;

    /**
     * 上次登录失败次数
     */
    @Column(name = "login_fail_times")
    private Integer loginFailTimes;

    /**
     * 账号创建者的账号ID
     */
    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_by")
    private Integer updatedBy;

    @Column(name = "updated_at")
    private Long updatedAt;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取管理员名称
     *
     * @return name - 管理员名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置管理员名称
     *
     * @param name 管理员名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取管理员手机
     *
     * @return phone - 管理员手机
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置管理员手机
     *
     * @param phone 管理员手机
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取管理员手机哈希值
     *
     * @return phone_hash - 管理员手机哈希值
     */
    public String getPhoneHash() {
        return phoneHash;
    }

    /**
     * 设置管理员手机哈希值
     *
     * @param phoneHash 管理员手机哈希值
     */
    public void setPhoneHash(String phoneHash) {
        this.phoneHash = phoneHash == null ? null : phoneHash.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取管理员备注
     *
     * @return remark - 管理员备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置管理员备注
     *
     * @param remark 管理员备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取上次成功登录时间
     *
     * @return last_login_at - 上次成功登录时间
     */
    public Long getLastLoginAt() {
        return lastLoginAt;
    }

    /**
     * 设置上次成功登录时间
     *
     * @param lastLoginAt 上次成功登录时间
     */
    public void setLastLoginAt(Long lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    /**
     * 获取上次登录失败次数
     *
     * @return login_fail_times - 上次登录失败次数
     */
    public Integer getLoginFailTimes() {
        return loginFailTimes;
    }

    /**
     * 设置上次登录失败次数
     *
     * @param loginFailTimes 上次登录失败次数
     */
    public void setLoginFailTimes(Integer loginFailTimes) {
        this.loginFailTimes = loginFailTimes;
    }

    /**
     * 获取账号创建者的账号ID
     *
     * @return created_by - 账号创建者的账号ID
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置账号创建者的账号ID
     *
     * @param createdBy 账号创建者的账号ID
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return created_at
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt
     */
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return updated_by
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * @param updatedBy
     */
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * @return updated_at
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt
     */
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 关联的角色
     */
    private List<AdminRole> roles;

    public List<AdminRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AdminRole> roles) {
        this.roles = roles;
    }
}