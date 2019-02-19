package net.realme.scm.wms.delhivery.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.framework.web.controller.BaseController;
import net.realme.scm.wms.domain.WmsShippingNotifyDto;
import net.realme.scm.wms.facade.WmsShippingNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@RestController("/inventory")
public class ShippingController extends BaseController {
    @Autowired
    WmsShippingNotifyService wmsShippingNotifyService;

    @ApiOperation("获取列表")
    @ApiImplicitParams({@ApiImplicitParam(name = "sku", value = "sku", paramType = "form", required = true),
     @ApiImplicitParam(name = "vendor", value = "销售商", paramType = "form", required = true
    )})
    @GetMapping({"/list"})
    public Result query(@Min(1L) int page, @Max(1000L) int limit) {
        ResultList<WmsShippingNotifyDto> data = this.wmsShippingNotifyService.get(page, limit);
        return this.ok(data);
    }

    @ApiOperation("获取运单号")
    @ApiImplicitParams({@ApiImplicitParam(name = "orderNo", value = "orderNo", paramType = "form", required = true),
            @ApiImplicitParam(name = "vendor", value = "销售商", paramType = "form", required = true
            )})
    @GetMapping("/queryWayBilByOrderId")
    public ResultT queryWayBilByOrderId(String orderNo) {
       WmsShippingNotifyDto wmsShippingNotifyDto = wmsShippingNotifyService.queryWayBilByOrderId(orderNo);
       return ResultT.success(wmsShippingNotifyDto);
    }
}
