package net.realme.mall.store.user.req;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeleteAddressReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "ssoid", value = "用户唯一标识", dataType = "Long", required = false, example = "54625L", hidden = true)
	private Long ssoid;

	@ApiModelProperty(name = "siteCode", value = "站点编号", dataType = "String", required = false, example = "in", hidden = true)
	private String siteCode;

	@ApiModelProperty(name = "id", value = "收货地址唯一标识", dataType = "String", required = true, example = "54565")
	@NotBlank(message = "address id is required")
	@Pattern(regexp = "^\\d{1,19}$", message = "address id wrong format")
	private String id;

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

	@Override
	public String toString() {
		return "DeleteAddressReq [ssoid=" + ssoid + ", siteCode=" + siteCode + ", id=" + id + "]";
	}

}
