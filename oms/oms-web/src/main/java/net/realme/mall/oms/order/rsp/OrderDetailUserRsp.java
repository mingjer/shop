package net.realme.mall.oms.order.rsp;

import java.io.Serializable;

/**
 * OMS订单详情-用户信息
 */
public class OrderDetailUserRsp implements Serializable {

    /**
     * 订单号
     */
    private Long userId;

    private String userName;
    private String nickName;
    private String mobile;
    private String mail;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
