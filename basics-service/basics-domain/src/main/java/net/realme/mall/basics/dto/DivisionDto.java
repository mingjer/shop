package net.realme.mall.basics.dto;

import java.io.Serializable;

public class DivisionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String divisionId;

	private String divisionCode;

	private String divisionType;

	private String divisionName;

	private String parentId;

	private String parentName;

	private String idPath;

	private String namePath;

	private Byte hasChild;

	private String countryCode;

	private Byte status;

	private Integer sequence;

	private Long createdAt;

	private Long updatedAt;

	private Integer createdBy;

	private Integer updatedBy;

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

	public String getIdPath() {
		return idPath;
	}

	public void setIdPath(String idPath) {
		this.idPath = idPath;
	}

	public String getNamePath() {
		return namePath;
	}

	public void setNamePath(String namePath) {
		this.namePath = namePath;
	}

	public Byte getHasChild() {
		return hasChild;
	}

	public void setHasChild(Byte hasChild) {
		this.hasChild = hasChild;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
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

	@Override
	public String toString() {
		return "DivisionDto [id=" + id + ", divisionId=" + divisionId + ", divisionCode=" + divisionCode
				+ ", divisionType=" + divisionType + ", divisionName=" + divisionName + ", parentId=" + parentId
				+ ", parentName=" + parentName + ", idPath=" + idPath + ", namePath=" + namePath + ", hasChild="
				+ hasChild + ", countryCode=" + countryCode + ", status=" + status + ", sequence=" + sequence
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", createdBy=" + createdBy + ", updatedBy="
				+ updatedBy + "]";
	}

}