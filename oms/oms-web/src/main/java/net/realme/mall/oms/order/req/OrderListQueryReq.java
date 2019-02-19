package net.realme.mall.oms.order.req;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderListQueryReq implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "beginTime", value = "开始时间", dataType = "String", required = false, example = "")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date beginTime;

	@ApiModelProperty(name = "endTime", value = "结束时间", dataType = "String", required = false, example = "")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	@ApiModelProperty(name = "orderNo", value = "订单号", dataType = "String", required = false, example = "")
	private Long orderNo;

	/**
	 * 11 Created, 21 Unpaid, 22 Paid, 31 Pushed,  32 Deliverying, 41 Finished, 42 Cancelled
	 */
	@ApiModelProperty(name = "orderNo", value = "订单状态", dataType = "Integer", required = false, example = "")
	private Integer orderStatus;

	private Integer page = 1;
	private Integer limit = 20;

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "OrderListQueryReq{" +
				"beginTime=" + beginTime +
				", endTime=" + endTime +
				", orderNo=" + orderNo +
				", orderStatus=" + orderStatus +
				", page=" + page +
				", limit=" + limit +
				'}';
	}
}
