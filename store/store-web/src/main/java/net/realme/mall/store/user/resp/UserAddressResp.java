package net.realme.mall.store.user.resp;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserAddressResp implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

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

	/**
	 * 是否默认地址
	 */
	private Byte isDefault;

	private BigDecimal longitude;

	private BigDecimal latitude;

	private Long createdAt;

	private Long updatedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public Byte getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Byte isDefault) {
		this.isDefault = isDefault;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
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
				+ cityName + ", countyName=" + countyName + ", countyId=" + countyId + ", address1=" + address1
				+ ", address2=" + address2 + ", email=" + email + ", fullName=" + fullName + ", postCode=" + postCode
				+ ", phoneCallingCodes=" + phoneCallingCodes + ", phoneNumber=" + phoneNumber + ", isDefault="
				+ isDefault + ", longitude=" + longitude + ", latitude=" + latitude + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}
