package net.realme.mall.user.model;

import javax.persistence.*;

@Table(name = "reserve_info")
public class ReserveInfo {
    /**
     * 自增长ID
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 预约的商品的组合属性id
     */
    @Column(name = "sku_id")
    private Integer skuId;

    /**
     * 用户预约的邮箱
     */
    private String email;

    /**
     * 用户对应的手机号
     */
    private String phone;

    /**
     * 预约商品的数量
     */
    @Column(name = "product_num")
    private Integer productNum;

    /**
     * 预约时间
     */
    @Column(name = "create_time")
    private Long createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Long updateTime;

    /**
     * 预约购买状态,0：预约中；1：购买成功；2：预约抢购失败
     */
    @Column(name = "reserve_status")
    private Byte reserveStatus;

    /**
     * 0：正常，1：已删除
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
     * 获取预约的商品的组合属性id
     *
     * @return sku_id - 预约的商品的组合属性id
     */
    public Integer getSkuId() {
        return skuId;
    }

    /**
     * 设置预约的商品的组合属性id
     *
     * @param skuId 预约的商品的组合属性id
     */
    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    /**
     * 获取用户预约的邮箱
     *
     * @return email - 用户预约的邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置用户预约的邮箱
     *
     * @param email 用户预约的邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取用户对应的手机号
     *
     * @return phone - 用户对应的手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置用户对应的手机号
     *
     * @param phone 用户对应的手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取预约商品的数量
     *
     * @return product_num - 预约商品的数量
     */
    public Integer getProductNum() {
        return productNum;
    }

    /**
     * 设置预约商品的数量
     *
     * @param productNum 预约商品的数量
     */
    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    /**
     * 获取预约时间
     *
     * @return create_time - 预约时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 设置预约时间
     *
     * @param createTime 预约时间
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
     * 获取预约购买状态,0：预约中；1：购买成功；2：预约抢购失败
     *
     * @return reserve_status - 预约购买状态,0：预约中；1：购买成功；2：预约抢购失败
     */
    public Byte getReserveStatus() {
        return reserveStatus;
    }

    /**
     * 设置预约购买状态,0：预约中；1：购买成功；2：预约抢购失败
     *
     * @param reserveStatus 预约购买状态,0：预约中；1：购买成功；2：预约抢购失败
     */
    public void setReserveStatus(Byte reserveStatus) {
        this.reserveStatus = reserveStatus;
    }

    /**
     * 获取0：正常，1：已删除
     *
     * @return status - 0：正常，1：已删除
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置0：正常，1：已删除
     *
     * @param status 0：正常，1：已删除
     */
    public void setStatus(Byte status) {
        this.status = status;
    }
}