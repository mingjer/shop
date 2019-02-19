package net.realme.mall.store.user.resp;

import java.io.Serializable;

public class DivisionVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String divisionId;

	private String divisionCode;

	private String divisionType;

	private String divisionName;

	private String parentId;

	private String parentName;

	private String countryCode;

	private Long createdAt;

	private Long updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}

	public String getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(String divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getDivisionType() {
		return divisionType;
	}

	public void setDivisionType(String divisionType) {
		this.divisionType = divisionType;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
		return "DivisionVo [id=" + id + ", divisionId=" + divisionId + ", divisionCode=" + divisionCode
				+ ", divisionType=" + divisionType + ", divisionName=" + divisionName + ", parentId=" + parentId
				+ ", parentName=" + parentName + ", countryCode=" + countryCode + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}