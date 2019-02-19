package net.realme.mall.oms.basics.req;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceSiteCancelReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "id", value = "服务站点唯一标识", dataType = "String", required = true, example = "54625")
	@NotBlank(message = "service site id is required")
	@Pattern(regexp = "^\\d{1,10}$", message = "service site id wrong format")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ServiceSiteCancelReq [id=" + id + "]";
	}

}
