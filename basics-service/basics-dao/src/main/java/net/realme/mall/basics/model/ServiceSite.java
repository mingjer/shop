package net.realme.mall.basics.model;

import javax.persistence.*;

@Table(name = "service_site")
public class ServiceSite {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 网点名称
     */
    private String name;

    /**
     * 所属站点编号
     */
    @Column(name = "site_code")
    private String siteCode;

    /**
     * 国家代码
     */
    @Column(name = "country_id")
    private String countryId;

    /**
     * 国家名称
     */
    @Column(name = "country_name")
    private String countryName;

    /**
     * 省/邦/区域代码
     */
    @Column(name = "province_id")
    private String provinceId;

    /**
     * 省/邦/区域名称
     */
    @Column(name = "province_name")
    private String provinceName;

    /**
     * 城市代码
     */
    @Column(name = "city_id")
    private String cityId;

    /**
     * 城市名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 网点详细地址
     */
    private String address;

    /**
     * 联系电话
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 邮编
     */
    @Column(name = "post_code")
    private String postCode;

    /**
     * 网点状态，0-禁用，1-启用
     */
    private Byte status;

    /**
     * 网点类型，1-独家，2-授权，3-接收点
     */
    private Byte type;

    /**
     * 维修级别
     */
    @Column(name = "repair_level")
    private Byte repairLevel;

    /**
     * 营业时间（星期）
     */
    @Column(name = "open_time_week")
    private String openTimeWeek;

    /**
     * 营业时间
     */
    @Column(name = "open_time")
    private String openTime;

    /**
     * 休息时间（星期）
     */
    @Column(name = "close_time")
    private String closeTime;

    /**
     * 备注信息
     */
    private String remark;

    /**
     * 归属类型，1-realme，2-oppo
     */
    @Column(name = "owner_type")
    private Byte ownerType;

    /**
     * 审核状态，0-已保存，1-待审核，2-审核通过，3-驳回
     */
    @Column(name = "assess_status")
    private Byte assessStatus;

    /**
     * 审核类型，1-新增，2-撤销 ，3-信息变更
     */
    @Column(name = "assess_type")
    private Byte assessType;

    /**
     * 审核数据
     */
    @Column(name = "assess_data")
    private String assessData;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 更新人
     */
    @Column(name = "updated_by")
    private Integer updatedBy;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Long createdAt;

    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private Long updatedAt;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取网点名称
     *
     * @return name - 网点名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置网点名称
     *
     * @param name 网点名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取所属站点编号
     *
     * @return site_code - 所属站点编号
     */
    public String getSiteCode() {
        return siteCode;
    }

    /**
     * 设置所属站点编号
     *
     * @param siteCode 所属站点编号
     */
    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode == null ? null : siteCode.trim();
    }

    /**
     * 获取国家代码
     *
     * @return country_id - 国家代码
     */
    public String getCountryId() {
        return countryId;
    }

    /**
     * 设置国家代码
     *
     * @param countryId 国家代码
     */
    public void setCountryId(String countryId) {
        this.countryId = countryId == null ? null : countryId.trim();
    }

    /**
     * 获取国家名称
     *
     * @return country_name - 国家名称
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * 设置国家名称
     *
     * @param countryName 国家名称
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName == null ? null : countryName.trim();
    }

    /**
     * 获取省/邦/区域代码
     *
     * @return province_id - 省/邦/区域代码
     */
    public String getProvinceId() {
        return provinceId;
    }

    /**
     * 设置省/邦/区域代码
     *
     * @param provinceId 省/邦/区域代码
     */
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId == null ? null : provinceId.trim();
    }

    /**
     * 获取省/邦/区域名称
     *
     * @return province_name - 省/邦/区域名称
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 设置省/邦/区域名称
     *
     * @param provinceName 省/邦/区域名称
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * 获取城市代码
     *
     * @return city_id - 城市代码
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * 设置城市代码
     *
     * @param cityId 城市代码
     */
    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    /**
     * 获取城市名称
     *
     * @return city_name - 城市名称
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 设置城市名称
     *
     * @param cityName 城市名称
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * 获取网点详细地址
     *
     * @return address - 网点详细地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置网点详细地址
     *
     * @param address 网点详细地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取联系电话
     *
     * @return phone_number - 联系电话
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置联系电话
     *
     * @param phoneNumber 联系电话
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * 获取经度
     *
     * @return longitude - 经度
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * 设置经度
     *
     * @param longitude 经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    /**
     * 获取纬度
     *
     * @return latitude - 纬度
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * 设置纬度
     *
     * @param latitude 纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    /**
     * 获取邮编
     *
     * @return post_code - 邮编
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * 设置邮编
     *
     * @param postCode 邮编
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    /**
     * 获取网点状态，0-禁用，1-启用
     *
     * @return status - 网点状态，0-禁用，1-启用
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置网点状态，0-禁用，1-启用
     *
     * @param status 网点状态，0-禁用，1-启用
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取网点类型，1-独家，2-授权，3-接收点
     *
     * @return type - 网点类型，1-独家，2-授权，3-接收点
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置网点类型，1-独家，2-授权，3-接收点
     *
     * @param type 网点类型，1-独家，2-授权，3-接收点
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取维修级别
     *
     * @return repair_level - 维修级别
     */
    public Byte getRepairLevel() {
        return repairLevel;
    }

    /**
     * 设置维修级别
     *
     * @param repairLevel 维修级别
     */
    public void setRepairLevel(Byte repairLevel) {
        this.repairLevel = repairLevel;
    }

    /**
     * 获取营业时间（星期）
     *
     * @return open_time_week - 营业时间（星期）
     */
    public String getOpenTimeWeek() {
        return openTimeWeek;
    }

    /**
     * 设置营业时间（星期）
     *
     * @param openTimeWeek 营业时间（星期）
     */
    public void setOpenTimeWeek(String openTimeWeek) {
        this.openTimeWeek = openTimeWeek == null ? null : openTimeWeek.trim();
    }

    /**
     * 获取营业时间
     *
     * @return open_time - 营业时间
     */
    public String getOpenTime() {
        return openTime;
    }

    /**
     * 设置营业时间
     *
     * @param openTime 营业时间
     */
    public void setOpenTime(String openTime) {
        this.openTime = openTime == null ? null : openTime.trim();
    }

    /**
     * 获取休息时间（星期）
     *
     * @return close_time - 休息时间（星期）
     */
    public String getCloseTime() {
        return closeTime;
    }

    /**
     * 设置休息时间（星期）
     *
     * @param closeTime 休息时间（星期）
     */
    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime == null ? null : closeTime.trim();
    }

    /**
     * 获取备注信息
     *
     * @return remark - 备注信息
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注信息
     *
     * @param remark 备注信息
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取归属类型，1-realme，2-oppo
     *
     * @return owner_type - 归属类型，1-realme，2-oppo
     */
    public Byte getOwnerType() {
        return ownerType;
    }

    /**
     * 设置归属类型，1-realme，2-oppo
     *
     * @param ownerType 归属类型，1-realme，2-oppo
     */
    public void setOwnerType(Byte ownerType) {
        this.ownerType = ownerType;
    }

    /**
     * 获取审核状态，0-已保存，1-待审核，2-审核通过，3-驳回
     *
     * @return assess_status - 审核状态，0-已保存，1-待审核，2-审核通过，3-驳回
     */
    public Byte getAssessStatus() {
        return assessStatus;
    }

    /**
     * 设置审核状态，0-已保存，1-待审核，2-审核通过，3-驳回
     *
     * @param assessStatus 审核状态，0-已保存，1-待审核，2-审核通过，3-驳回
     */
    public void setAssessStatus(Byte assessStatus) {
        this.assessStatus = assessStatus;
    }

    /**
     * 获取审核类型，1-新增，2-撤销 ，3-信息变更
     *
     * @return assess_type - 审核类型，1-新增，2-撤销 ，3-信息变更
     */
    public Byte getAssessType() {
        return assessType;
    }

    /**
     * 设置审核类型，1-新增，2-撤销 ，3-信息变更
     *
     * @param assessType 审核类型，1-新增，2-撤销 ，3-信息变更
     */
    public void setAssessType(Byte assessType) {
        this.assessType = assessType;
    }

    /**
     * 获取审核数据
     *
     * @return assess_data - 审核数据
     */
    public String getAssessData() {
        return assessData;
    }

    /**
     * 设置审核数据
     *
     * @param assessData 审核数据
     */
    public void setAssessData(String assessData) {
        this.assessData = assessData == null ? null : assessData.trim();
    }

    /**
     * 获取创建人
     *
     * @return created_by - 创建人
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取更新人
     *
     * @return updated_by - 更新人
     */
    public Integer getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置更新人
     *
     * @param updatedBy 更新人
     */
    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取创建时间
     *
     * @return created_at - 创建时间
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置创建时间
     *
     * @param createdAt 创建时间
     */
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取更新时间
     *
     * @return updated_at - 更新时间
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 设置更新时间
     *
     * @param updatedAt 更新时间
     */
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }
}