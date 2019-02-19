package net.realme.mall.oms.basics.req;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceSiteReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "服务站点唯一标识", dataType = "String", required = false, example = "54625")
	@Length(min = 0, max = 10, message = "service site id wrong format")
	private String id;

	@ApiModelProperty(name = "assessType", value = "申请类型，1-新增，2-撤销 ，3-信息变更", dataType = "String", required = false, allowableValues = "1,2,3", example = "1")
	@Length(min = 0, max = 1, message = "assess type wrong format")
	private String assessType;

	@ApiModelProperty(name = "name", value = "服务网点名称", dataType = "String", required = false, example = "carljack")
	@Length(min = 0, max = 100, message = "service site name wrong format")
	private String name;

	@ApiModelProperty(name = "ownerType", value = "服务网点归属，1-realme，2-oppo", dataType = "String", required = false, allowableValues = "1,2", example = "1")
	@Length(min = 0, max = 1, message = "owner type wrong format")
	private String ownerType;

	@ApiModelProperty(name = "type", value = "服务网点类型，1-独家，2-授权，3-接收点", dataType = "String", required = false, allowableValues = "1,2,3", example = "1")
	@Length(min = 0, max = 1, message = "type wrong format")
	private String type;

	@ApiModelProperty(name = "postCode", value = "邮编号码", dataType = "String", required = false, example = "71452")
	@Length(min = 0, max = 20, message = "post code wrong format")
	private String postCode;

	@ApiModelProperty(name = "countryId", value = "国家id", dataType = "String", required = false, example = "5215")
	@Length(min = 0, max = 30, message = "country id wrong format")
	private String countryId;

	@ApiModelProperty(name = "countryName", value = "国家名称", dataType = "String", required = false, example = "India")
	@Length(min = 0, max = 50, message = "country name wrong format")
	private String countryName;

	@ApiModelProperty(name = "provinceId", value = "省份id", dataType = "String", required = false, example = "6145")
	@Length(min = 0, max = 30, message = "province id wrong format")
	private String provinceId;

	@ApiModelProperty(name = "provinceName", value = "省份名称", dataType = "String", required = false, example = "Delhi")
	@Length(min = 0, max = 100, message = "province name wrong format")
	private String provinceName;

	@ApiModelProperty(name = "cityId", value = "城市id", dataType = "String", required = false, example = "61452")
	@Length(min = 0, max = 30, message = "city id wrong format")
	private String cityId;

	@ApiModelProperty(name = "cityName", value = "城市名称", dataType = "String", required = false, example = "Lajpatnagar")
	@Length(min = 0, max = 100, message = "city name wrong format")
	private String cityName;

	@ApiModelProperty(name = "address", value = "详细地址信息", dataType = "String", required = false, example = "M57 , First Floor, Part-2, Lajpat Nagar,Lajpatnagar,Delhi")
	@Length(min = 0, max = 500, message = "address wrong format")
	private String address;

	@ApiModelProperty(name = "phoneNumber", value = "联系电话", dataType = "String", required = false, example = "1141618154")
	@Length(min = 0, max = 50, message = "phone number wrong format")
	private String phoneNumber;

	@ApiModelProperty(name = "longitude", value = "经度", dataType = "String", required = false, example = "77.24456")
	@Length(min = 0, max = 20, message = "longitude wrong format")
	private String longitude;

	@ApiModelProperty(name = "latitude", value = "纬度", dataType = "String", required = false, example = "28.56743")
	@Length(min = 0, max = 20, message = "latitude wrong format")
	private String latitude;

	@ApiModelProperty(name = "openTimeWeek", value = "营业时间（星期）", dataType = "String", required = false, example = "Monday,Sunday")
	@Length(min = 0, max = 50, message = "open time week wrong format")
	private String openTimeWeek;

	@ApiModelProperty(name = "openTime", value = "营业时间", dataType = "String", required = false, example = "10:00,08:00")
	@Length(min = 0, max = 50, message = "open time wrong format")
	private String openTime;

	@ApiModelProperty(name = "closeTime", value = "休息时间（星期）", dataType = "String", required = false, example = "Monday,Sunday")
	@Length(min = 0, max = 30, message = "close time wrong format")
	private String closeTime;

	@ApiModelProperty(name = "remark", value = "备注信息", dataType = "String", required = false, example = "拆迁撤销网点")
	@Length(min = 0, max = 100, message = "remark wrong format")
	private String remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAssessType() {
		return assessType;
	}

	public void setAssessType(String assessType) {
		this.assessType = assessType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
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

	@Override
	public String toString() {
		return "ServiceSiteReq [id=" + id + ", assessType=" + assessType + ", name=" + name + ", ownerType=" + ownerType
				+ ", type=" + type + ", postCode=" + postCode + ", countryId=" + countryId + ", countryName="
				+ countryName + ", provinceId=" + provinceId + ", provinceName=" + provinceName + ", cityId=" + cityId
				+ ", cityName=" + cityName + ", address=" + address + ", phoneNumber=" + phoneNumber + ", longitude="
				+ longitude + ", latitude=" + latitude + ", openTimeWeek=" + openTimeWeek + ", openTime=" + openTime
				+ ", closeTime=" + closeTime + ", remark=" + remark + "]";
	}

}
