package net.realme.mall.oms.admin.model;

import javax.persistence.*;

@Table(name = "admin_user_role")
public class AdminUserRole {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 管理员账号ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 角色代码
     */
    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_at")
    private Long createdAt;

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
     * 获取管理员账号ID
     *
     * @return user_id - 管理员账号ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置管理员账号ID
     *
     * @param userId 管理员账号ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取角色代码
     *
     * @return role_id - 角色代码
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色代码
     *
     * @param roleId 角色代码
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return created_by
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
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
}