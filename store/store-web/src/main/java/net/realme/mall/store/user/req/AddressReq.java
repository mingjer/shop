package net.realme.mall.store.user.req;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "ssoid", value = "用户唯一标识", dataType = "Long", required = false, example = "54625L", hidden = true)
	private Long ssoid;

	@ApiModelProperty(name = "siteCode", value = "站点编号", dataType = "String", required = false, example = "in", hidden = true)
	private String siteCode;

	@ApiModelProperty(name = "id", value = "收货地址唯一标识", dataType = "String", required = false, example = "54625")
	private String id;

	@ApiModelProperty(name = "pinCode", value = "邮编号码（pincode）", dataType = "String", required = false, example = "71452", hidden = true)
	@Length(min = 0, max = 20, message = "{pincode.wrong.format}")
	private String pinCode;

	@ApiModelProperty(name = "postCode", value = "邮编号码", dataType = "String", required = false, example = "71452")
	@NotBlank(message = "{post.code.required}")
	@Length(min = 1, max = 20, message = "{post.code.wrong.format}")
	private String postCode;

	@ApiModelProperty(name = "fullName", value = "收货人全称", dataType = "String", required = true, example = "carljack")
	@NotBlank(message = "{full.name.required}")
	@Pattern(regexp = "^.{1,500}$", message = "{full.name.wrong.format}")
	private String fullName;

	@ApiModelProperty(name = "countryId", value = "国家id", dataType = "String", required = false, example = "5215")
	@Length(min = 0, max = 30, message = "{country.id.wrong.format}")
	private String countyId;

	@ApiModelProperty(name = "countryName", value = "国家名称", dataType = "String", required = false, example = "India")
	@Length(min = 0, max = 50, message = "{country.name.wrong.format}")
	private String countyName;

	@ApiModelProperty(name = "provinceId", value = "省份id", dataType = "String", required = false, example = "6145")
	@Length(min = 0, max = 30, message = "province.id.wrong.format")
	private String provinceId;

	@ApiModelProperty(name = "provinceName", value = "省份名称", dataType = "String", required = false, example = "Delhi")
	@NotBlank(message = "{province.name.required}")
	@Pattern(regexp = "^.{1,500}$", message = "{province.name.wrong.format}")
	private String provinceName;

	@ApiModelProperty(name = "cityId", value = "城市id", dataType = "String", required = false, example = "61452")
	@Length(min = 0, max = 30, message = "{city.id.wrong.format}")
	private String cityId;

	@ApiModelProperty(name = "cityName", value = "城市名称", dataType = "String", required = false, example = "Lajpatnagar")
	@NotBlank(message = "{city.name.required}")
	@Pattern(regexp = "^.{1,500}$", message = "{city.name.wrong.format}")
	private String cityName;

	@ApiModelProperty(name = "townId", value = "城镇编号", dataType = "String", required = false, example = "5421", hidden = true)
	@Length(min = 0, max = 30, message = "{town.id.wrong.format}")
	private String townId;

	@ApiModelProperty(name = "townName", value = "城镇名称", dataType = "String", required = false, example = "广宁镇", hidden = true)
	@Length(min = 0, max = 100, message = "{town.name.wrong.format}")
	private String townName;

	@ApiModelProperty(name = "address1", value = "详细地址信息", dataType = "String", required = true, example = "M57 , First Floor, Part-2, Lajpat Nagar,Lajpatnagar,Delhi")
	@NotBlank(message = "{detail.address.required}")
	@Pattern(regexp = "^.{1,500}$", message = "{detail.address.wrong.format}")
	private String address1;

	@ApiModelProperty(name = "address2", value = "地标信息", dataType = "String", required = false, example = "First Floor")
	@Length(min = 0, max = 500, message = "{address.wrong.format}")
	private String address2;

	@ApiModelProperty(name = "phoneCallingCodes", value = "联系电话区号", dataType = "String", required = false, example = "+96")
	@NotBlank(message = "{phone.call.code.required}")
	@Length(min = 0, max = 10, message = "{phone.call.code.wrong.format}")
	private String phoneCallingCodes;

	@ApiModelProperty(name = "phoneNumber", value = "联系电话", dataType = "String", required = true, example = "1141618154")
	@NotBlank(message = "{phone.number.required}")
	@Pattern(regexp = "^\\d{10}$", message = "{phone.number.wrong.format}")
	private String phoneNumber;

	@ApiModelProperty(name = "longitude", value = "经度", dataType = "String", required = false, example = "77.24456")
	@Length(min = 0, max = 20, message = "{longitude.wrong.format}")
	private String longitude;

	@ApiModelProperty(name = "latitude", value = "纬度", dataType = "String", required = false, example = "28.56743")
	@Length(min = 0, max = 20, message = "{latitude.wrong.format}")
	private String latitude;

	@ApiModelProperty(name = "email", value = "邮箱地址", dataType = "String", required = true, example = "565845@qq.com")
	@Email(message = "{email.wrong.format}")
	private String email;

	@ApiModelProperty(name = "isDefault", value = "是否默认地址，0-非默认，1-默认", dataType = "String", required = true, allowableValues = "0,1", example = "1")
	@NotBlank(message = "{address.default.required}")
	@Pattern(regexp = "^[0-1]*$", message = "{address.default.wrong.format}")
	private String isDefault;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCountyId() {
		return countyId;
	}

	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
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

	public String getTownId() {
		return townId;
	}

	public void setTownId(String townId) {
		this.townId = townId;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "AddressReq [ssoid=" + ssoid + ", siteCode=" + siteCode + ", id=" + id + ", pinCode=" + pinCode
				+ ", postCode=" + postCode + ", fullName=" + fullName + ", countyId=" + countyId + ", countyName="
				+ countyName + ", provinceId=" + provinceId + ", provinceName=" + provinceName + ", cityId=" + cityId
				+ ", cityName=" + cityName + ", townId=" + townId + ", townName=" + townName + ", address1=" + address1
				+ ", address2=" + address2 + ", phoneCallingCodes=" + phoneCallingCodes + ", phoneNumber=" + phoneNumber
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", email=" + email + ", isDefault="
				+ isDefault + "]";
	}

}
