package net.realme.mall.oms.basics.req;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceSiteReviewReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "服务站点唯一标识", dataType = "String", required = true, example = "54625")
	@NotBlank(message = "service site id is required")
	@Pattern(regexp = "^\\d{1,10}$", message = "service site id wrong format")
	private String id;

	@ApiModelProperty(name = "assessStatus", value = "审核状态，2-审核通过，3-驳回", dataType = "String", required = true, allowableValues = "2,3", example = "2")
	@NotBlank(message = "assess status is required")
	@Pattern(regexp = "^[2-3]*$", message = "assess status is wrong format")
	private String assessStatus;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAssessStatus() {
		return assessStatus;
	}

	public void setAssessStatus(String assessStatus) {
		this.assessStatus = assessStatus;
	}

	@Override
	public String toString() {
		return "ServiceSiteReviewReq [id=" + id + ", assessStatus=" + assessStatus + "]";
	}

}
