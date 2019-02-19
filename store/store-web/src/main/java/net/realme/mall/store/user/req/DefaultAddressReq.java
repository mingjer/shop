package net.realme.mall.store.user.req;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultAddressReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "ssoid", value = "用户唯一标识", dataType = "Long", required = false, example = "54625L", hidden = true)
	private Long ssoid;

	@ApiModelProperty(name = "siteCode", value = "站点编号", dataType = "String", required = false, example = "in", hidden = true)
	private String siteCode;

	@ApiModelProperty(name = "id", value = "收货地址唯一标识", dataType = "String", required = true, example = "54625")
	@NotBlank(message = "address id is required")
	@Pattern(regexp = "^\\d{1,19}$", message = "address id wrong format")
	private String id;

	@ApiModelProperty(name = "isDefault", value = "是否默认地址，0-非默认，1-默认", dataType = "String", required = true, allowableValues = "0,1", example = "1")
	@NotBlank(message = "default param is required")
	@Pattern(regexp = "^[0-1]*$", message = "default value wrong format")
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

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "DefaultAddressReq [ssoid=" + ssoid + ", siteCode=" + siteCode + ", id=" + id + ", isDefault="
				+ isDefault + "]";
	}

}
