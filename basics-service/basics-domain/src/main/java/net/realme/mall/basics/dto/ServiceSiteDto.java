package net.realme.mall.basics.dto;

import java.io.Serializable;

public class ServiceSiteDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String siteCode;

	private String countryId;

	private String countryName;

	private String provinceId;

	private String provinceName;

	private String cityId;

	private String cityName;

	private String address;

	private String phoneNumber;

	private String longitude;

	private String latitude;

	private String postCode;

	private Byte status;

	/**
	 * 网点类型，1-独家（exclusive），2-授权（authorized） ，3-接收点（Receive Point）
	 */
	private Byte type;

	/**
	 * 维修级别
	 */
	private Byte repairLevel;

	/**
	 * 营业时间（星期）
	 */
	private String openTimeWeek;

	/**
	 * 营业时间
	 */
	private String openTime;

	/**
	 * 休息时间（星期）
	 */
	private String closeTime;

	private String remark;

	/**
	 * 归属类型，1-realme，2-oppo
	 */
	private Byte ownerType;

	/**
	 * 审核状态
	 */
	private Byte assessStatus;

	/**
	 * 审核类型
	 */
	private Byte assessType;

	/**
	 * 审核数据
	 */
	private String assessData;

	private Integer createdBy;

	private Integer updatedBy;

	private Long createdAt;

	private Long updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Byte getRepairLevel() {
		return repairLevel;
	}

	public void setRepairLevel(Byte repairLevel) {
		this.repairLevel = repairLevel;
	}

	public String getOpenTimeWeek() {
		return openTimeWeek;
	}

	public void setOpenTimeWeek(String openTimeWeek) {
		this.openTimeWeek = openTimeWeek;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Byte getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(Byte ownerType) {
		this.ownerType = ownerType;
	}

	public Byte getAssessStatus() {
		return assessStatus;
	}

	public void setAssessStatus(Byte assessStatus) {
		this.assessStatus = assessStatus;
	}

	public Byte getAssessType() {
		return assessType;
	}

	public void setAssessType(Byte assessType) {
		this.assessType = assessType;
	}

	public String getAssessData() {
		return assessData;
	}

	public void setAssessData(String assessData) {
		this.assessData = assessData;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
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
		return "ServiceSiteDto [id=" + id + ", name=" + name + ", siteCode=" + siteCode + ", countryId=" + countryId
				+ ", countryName=" + countryName + ", provinceId=" + provinceId + ", provinceName=" + provinceName
				+ ", cityId=" + cityId + ", cityName=" + cityName + ", address=" + address + ", phoneNumber="
				+ phoneNumber + ", longitude=" + longitude + ", latitude=" + latitude + ", postCode=" + postCode
				+ ", status=" + status + ", type=" + type + ", repairLevel=" + repairLevel + ", openTimeWeek="
				+ openTimeWeek + ", openTime=" + openTime + ", closeTime=" + closeTime + ", remark=" + remark
				+ ", ownerType=" + ownerType + ", assessStatus=" + assessStatus + ", assessType=" + assessType
				+ ", assessData=" + assessData + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}