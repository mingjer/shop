package net.realme.mall.user.model;

import javax.persistence.*;

@Table(name = "user_address")
public class UserAddress {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户ID
     */
    private Long ssoid;

    /**
     * 站点ID
     */
    @Column(name = "site_code")
    private String siteCode;

    /**
     * 印度PinCode
     */
    @Column(name = "pin_code")
    private String pinCode;

    /**
     * 省/邦ID
     */
    @Column(name = "province_id")
    private String provinceId;

    /**
     * 省/邦名称
     */
    @Column(name = "province_name")
    private String provinceName;

    /**
     * 城市ID
     */
    @Column(name = "city_id")
    private String cityId;

    /**
     * 城市名称
     */
    @Column(name = "city_name")
    private String cityName;

    /**
     * 县名称
     */
    @Column(name = "county_name")
    private String countyName;

    /**
     * 县ID
     */
    @Column(name = "county_id")
    private String countyId;

    /**
     * 镇名称
     */
    @Column(name = "town_name")
    private String townName;

    /**
     * 镇ID
     */
    @Column(name = "town_id")
    private String townId;

    /**
     * 详细地址
     */
    private String address1;

    /**
     * 地标
     */
    private String address2;

    /**
     * 收货人全称
     */
    @Column(name = "full_name")
    private String fullName;

    /**
     * 邮编
     */
    @Column(name = "post_code")
    private String postCode;

    /**
     * 手机号区号
     */
    @Column(name = "phone_calling_codes")
    private String phoneCallingCodes;

    /**
     * 联系电话
     */
    @Column(name = "phone_number")
    private String phoneNumber;

    /**
     * 有效状态，0-无效，1-有效
     */
    private Byte status;

    /**
     * 是否默认地址,0-非默认，1-默认
     */
    @Column(name = "is_default")
    private Byte isDefault;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

    /**
     * 邮箱地址
     */
    private String email;

    @Column(name = "geo_hash")
    private String geoHash;

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
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return ssoid - 用户ID
     */
    public Long getSsoid() {
        return ssoid;
    }

    /**
     * 设置用户ID
     *
     * @param ssoid 用户ID
     */
    public void setSsoid(Long ssoid) {
        this.ssoid = ssoid;
    }

    /**
     * 获取站点ID
     *
     * @return site_code - 站点ID
     */
    public String getSiteCode() {
        return siteCode;
    }

    /**
     * 设置站点ID
     *
     * @param siteCode 站点ID
     */
    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode == null ? null : siteCode.trim();
    }

    /**
     * 获取印度PinCode
     *
     * @return pin_code - 印度PinCode
     */
    public String getPinCode() {
        return pinCode;
    }

    /**
     * 设置印度PinCode
     *
     * @param pinCode 印度PinCode
     */
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode == null ? null : pinCode.trim();
    }

    /**
     * 获取省/邦ID
     *
     * @return province_id - 省/邦ID
     */
    public String getProvinceId() {
        return provinceId;
    }

    /**
     * 设置省/邦ID
     *
     * @param provinceId 省/邦ID
     */
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId == null ? null : provinceId.trim();
    }

    /**
     * 获取省/邦名称
     *
     * @return province_name - 省/邦名称
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 设置省/邦名称
     *
     * @param provinceName 省/邦名称
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * 获取城市ID
     *
     * @return city_id - 城市ID
     */
    public String getCityId() {
        return cityId;
    }

    /**
     * 设置城市ID
     *
     * @param cityId 城市ID
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
     * 获取县名称
     *
     * @return county_name - 县名称
     */
    public String getCountyName() {
        return countyName;
    }

    /**
     * 设置县名称
     *
     * @param countyName 县名称
     */
    public void setCountyName(String countyName) {
        this.countyName = countyName == null ? null : countyName.trim();
    }

    /**
     * 获取县ID
     *
     * @return county_id - 县ID
     */
    public String getCountyId() {
        return countyId;
    }

    /**
     * 设置县ID
     *
     * @param countyId 县ID
     */
    public void setCountyId(String countyId) {
        this.countyId = countyId == null ? null : countyId.trim();
    }

    /**
     * 获取镇名称
     *
     * @return town_name - 镇名称
     */
    public String getTownName() {
        return townName;
    }

    /**
     * 设置镇名称
     *
     * @param townName 镇名称
     */
    public void setTownName(String townName) {
        this.townName = townName == null ? null : townName.trim();
    }

    /**
     * 获取镇ID
     *
     * @return town_id - 镇ID
     */
    public String getTownId() {
        return townId;
    }

    /**
     * 设置镇ID
     *
     * @param townId 镇ID
     */
    public void setTownId(String townId) {
        this.townId = townId == null ? null : townId.trim();
    }

    /**
     * 获取详细地址
     *
     * @return address1 - 详细地址
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * 设置详细地址
     *
     * @param address1 详细地址
     */
    public void setAddress1(String address1) {
        this.address1 = address1 == null ? null : address1.trim();
    }

    /**
     * 获取地标
     *
     * @return address2 - 地标
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * 设置地标
     *
     * @param address2 地标
     */
    public void setAddress2(String address2) {
        this.address2 = address2 == null ? null : address2.trim();
    }

    /**
     * 获取收货人全称
     *
     * @return full_name - 收货人全称
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置收货人全称
     *
     * @param fullName 收货人全称
     */
    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
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
     * 获取手机号区号
     *
     * @return phone_calling_codes - 手机号区号
     */
    public String getPhoneCallingCodes() {
        return phoneCallingCodes;
    }

    /**
     * 设置手机号区号
     *
     * @param phoneCallingCodes 手机号区号
     */
    public void setPhoneCallingCodes(String phoneCallingCodes) {
        this.phoneCallingCodes = phoneCallingCodes == null ? null : phoneCallingCodes.trim();
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
     * 获取有效状态，0-无效，1-有效
     *
     * @return status - 有效状态，0-无效，1-有效
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置有效状态，0-无效，1-有效
     *
     * @param status 有效状态，0-无效，1-有效
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取是否默认地址,0-非默认，1-默认
     *
     * @return is_default - 是否默认地址,0-非默认，1-默认
     */
    public Byte getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否默认地址,0-非默认，1-默认
     *
     * @param isDefault 是否默认地址,0-非默认，1-默认
     */
    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
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
     * 获取邮箱地址
     *
     * @return email - 邮箱地址
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱地址
     *
     * @param email 邮箱地址
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return geo_hash
     */
    public String getGeoHash() {
        return geoHash;
    }

    /**
     * @param geoHash
     */
    public void setGeoHash(String geoHash) {
        this.geoHash = geoHash == null ? null : geoHash.trim();
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