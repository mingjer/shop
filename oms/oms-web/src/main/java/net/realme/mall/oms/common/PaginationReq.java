package net.realme.mall.oms.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.basics.req
 *
 * @author 91000044
 * @date 2018/7/26 12:02
 */
@ApiModel
public class PaginationReq implements Serializable {
    private static final long serialVersionUID = 4290432152201999464L;

    @ApiModelProperty(value = "页数", example = "1")
    @Min(1)
    private Integer page;
    @ApiModelProperty(value = "页大小", example = "20")
    @Max(1000)
    private Integer limit;

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
}
