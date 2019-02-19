package net.realme.mall.oms.basics.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.framework.web.controller.BaseController;
import net.realme.mall.basics.dto.DivisionDto;
import net.realme.mall.basics.facade.DivisionService;

@Api(tags = { "国家，省份，城市信息" })
@Validated
@RestController
@RequestMapping("/division")
public class DivisionController extends BaseController {

	@Autowired
	private DivisionService divisionService;

	@ApiOperation(value = "区域信息查询", notes = "区域信息查询，包括国家，省份，城市")
	@GetMapping("/list")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "countryId", value = "国家编码，查询该国家下的所有省市信息", paramType = "query", dataType = "String", required = false),
			@ApiImplicitParam(name = "provinceId", value = "省份编码，查询该省份下的所有城市信息", paramType = "query", dataType = "String", required = false) })
	public Result divisionList(String countryId, String provinceId) {
		ResultT<ResultList<DivisionDto>> list = null;
		if (StringUtils.isBlank(countryId)) {
			list = divisionService.getCountryList();
		} else {
			if (StringUtils.isBlank(provinceId)) {
				// 查询该国家下面的省市
				list = divisionService.getProvinceList(countryId);
			} else {
				// 查询该省市下的所有城市
				list = divisionService.getCityList(countryId, provinceId);
			}
		}
		return ok(list.getData());
	}

}
