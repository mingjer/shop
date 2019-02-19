package net.realme.mall.user.domain;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.user.domain
 *
 * @author 91000044
 * @date 2018/8/28 20:27
 */
public class UserAddressDto implements Serializable {
	private static final long serialVersionUID = -1949950083863526056L;

	@JsonSerialize(using=ToStringSerializer.class)
	private Long id;

	/**
	 * 用户ID
	 */
	private Long ssoid;

	/**
	 * 站点ID
	 */
	private String siteCode;

	/**
	 * 印度PinCode
	 */
	private String pinCode;

	/**
	 * 省/邦ID
	 */
	private String provinceId;

	/**
	 * 省/邦名称
	 */
	private String provinceName;

	/**
	 * 城市ID
	 */
	private String cityId;

	/**
	 * 城市名称
	 */
	private String cityName;

	/**
	 * 县名称
	 */
	private String countyName;

	/**
	 * 县ID
	 */
	private String countyId;

	/**
	 * 镇名称
	 */
	private String townName;

	/**
	 * 镇ID
	 */
	private String townId;

	/**
	 * 详细地址
	 */
	private String address1;

	private String address2;

	private String email;

	private String fullName;

	/**
	 * 邮编
	 */
	private String postCode;

	/**
	 * 手机号前缀
	 */
	private String phoneCallingCodes;

	private String phoneNumber;

	private Byte status;

	/**
	 * 是否默认地址
	 */
	private Byte isDefault;

	private String longitude;

	private String latitude;

	private String geohash;

	private Long createdAt;

	private Long updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSsoid() {
		return ssoid;
	}

	public void setSsoid(Long ssoid) {
		this.ssoid = ssoid;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getCountyId() {
		return countyId;
	}

	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public String getTownId() {
		return townId;
	}

	public void setTownId(String townId) {
		this.townId = townId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getPhoneCallingCodes() {
		return phoneCallingCodes;
	}

	public void setPhoneCallingCodes(String phoneCallingCodes) {
		this.phoneCallingCodes = phoneCallingCodes;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Byte isDefault) {
		this.isDefault = isDefault;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getGeohash() {
		return geohash;
	}

	public void setGeohash(String geohash) {
		this.geohash = geohash;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "UserAddressDto [id=" + id + ", ssoid=" + ssoid + ", siteCode=" + siteCode + ", pinCode=" + pinCode
				+ ", provinceId=" + provinceId + ", provinceName=" + provinceName + ", cityId=" + cityId + ", cityName="
				+ cityName + ", countyName=" + countyName + ", countyId=" + countyId + ", townName=" + townName
				+ ", townId=" + townId + ", address1=" + address1 + ", address2=" + address2 + ", email=" + email
				+ ", fullName=" + fullName + ", postCode=" + postCode + ", phoneCallingCodes=" + phoneCallingCodes
				+ ", phoneNumber=" + phoneNumber + ", status=" + status + ", isDefault=" + isDefault + ", longitude="
				+ longitude + ", latitude=" + latitude + ", geohash=" + geohash + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}
