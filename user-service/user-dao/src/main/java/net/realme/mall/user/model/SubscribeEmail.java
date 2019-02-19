package net.realme.mall.user.model;

import javax.persistence.*;

@Table(name = "subscribe_email")
public class SubscribeEmail {
    /**
     * 自增长ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 订阅者邮箱
     */
    private String email;

    /**
     * 订阅时间
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Long updateTime;

    /**
     * 上次发送时间
     */
    @Column(name = "send_time")
    private Long sendTime;

    /**
     * 0：订阅（有效数据），1：取消订阅（失效）
     */
    private Byte status;

    /**
     * 获取自增长ID
     *
     * @return id - 自增长ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增长ID
     *
     * @param id 自增长ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取订阅者邮箱
     *
     * @return email - 订阅者邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置订阅者邮箱
     *
     * @param email 订阅者邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取订阅时间
     *
     * @return create_time - 订阅时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置订阅时间
     *
     * @param createTime 订阅时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取上次发送时间
     *
     * @return send_time - 上次发送时间
     */
    public Long getSendTime() {
        return sendTime;
    }

    /**
     * 设置上次发送时间
     *
     * @param sendTime 上次发送时间
     */
    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    /**
     * 获取0：订阅（有效数据），1：取消订阅（失效）
     *
     * @return status - 0：订阅（有效数据），1：取消订阅（失效）
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0：订阅（有效数据），1：取消订阅（失效）
     *
     * @param status 0：订阅（有效数据），1：取消订阅（失效）
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}