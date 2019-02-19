package net.realme.mall.basics.dto;

import java.io.Serializable;

public class IndiaPinCodeDto implements Serializable {

	private static final long serialVersionUID = 1483974973698003141L;

	private Integer id;
	private String pinCode;
	private String countryId;
	private String countryName;
	private String provinceId;
	private String provinceName;
	private String cityId;
	private String cityName;
	private Byte prepaid;
	private Byte cod;
	private Long createdAt;
	private Long updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
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

	public Long getCreatedAt() {
		return createdAt;
	}

	public Byte getPrepaid() {
		return prepaid;
	}

	public void setPrepaid(Byte prepaid) {
		this.prepaid = prepaid;
	}

	public Byte getCod() {
		return cod;
	}

	public void setCod(Byte cod) {
		this.cod = cod;
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
		return "IndiaPinCodeDto [id=" + id + ", pinCode=" + pinCode + ", countryId=" + countryId + ", countryName="
				+ countryName + ", provinceId=" + provinceId + ", provinceName=" + provinceName + ", cityId=" + cityId
				+ ", cityName=" + cityName + ", prepaid=" + prepaid + ", cod=" + cod + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}
