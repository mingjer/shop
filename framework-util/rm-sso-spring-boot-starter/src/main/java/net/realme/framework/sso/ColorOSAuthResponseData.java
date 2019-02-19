package net.realme.framework.sso;

import java.io.Serializable;
import java.util.Map;

/**
 * 会员鉴权接口返回的用户信息
 * @author 91000044
 */
public class ColorOSAuthResponseData implements Serializable {

    private static final long serialVersionUID = 5168778489419474703L;
    private String ssoid;
    /**
     * 等同于ssoid
     */
    private String userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * OPPO帐号
     */
    private String accountName;
    private String status;
    private String birthday;
    private String sex;
    /**
     * key：规格（目前只有default）
     * value：地址
     */
    private Map<String, String> avatar;
    private Long createTime;

    public String getSsoid() {
        return ssoid;
    }

    public void setSsoid(String ssoid) {
        this.ssoid = ssoid;
        this.userId = ssoid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        this.ssoid = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountName() {
        return accountName;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ColorOSAuthResponseData{" +
                "ssoid='" + ssoid + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", accountName='" + accountName + '\'' +
                ", status='" + status + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", avatar=" + avatar +
                ", createTime=" + createTime +
                '}';
    }
}
