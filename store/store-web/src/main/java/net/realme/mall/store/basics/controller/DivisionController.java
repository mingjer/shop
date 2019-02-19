package net.realme.mall.store.basics.controller;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultCode;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.framework.util.exception.BusinessException;
import net.realme.framework.web.controller.LocalizeController;
import net.realme.mall.basics.dto.DivisionDto;
import net.realme.mall.basics.facade.DivisionService;
import net.realme.mall.store.user.resp.DivisionVo;

@Api(tags = { "国家，省份，城市基础信息" })
@Validated
@RestController
public class DivisionController extends LocalizeController {

	@Autowired
	private DivisionService divisionService;

	@ApiOperation(value = "省份，城市信息列表", notes = "根据站点编号，查询该站点下的省份，城市列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "siteCode", value = "站点编号", paramType = "path", dataType = "String", required = true),
			@ApiImplicitParam(name = "provinceId", value = "省份编码，查询该省份下的城市", paramType = "query", dataType = "String", required = false) })
	@GetMapping("division/list")
	public Result getDivisionList(@PathVariable String siteCode, @RequestParam(required = false) String provinceId) {
		ResultT<ResultList<DivisionDto>> result = null;
		if (StringUtils.isBlank(provinceId)) {
			result = divisionService.getProvinceList(siteCode.toUpperCase());
		} else {
			this.checkParam("^.{1,50}$", provinceId, "province.id.wrong.format");
			result = divisionService.getCityList(siteCode.toUpperCase(), provinceId);
		}
		if (result.getData() != null && result.getData().getRecords() != null
				&& !result.getData().getRecords().isEmpty()) {
			List<DivisionVo> respList = new LinkedList<>();
			List<DivisionDto> list = result.getData().getRecords();
			for (DivisionDto divisionDto : list) {
				DivisionVo resp = this.convertFromDivisionDto(divisionDto);
				respList.add(resp);
			}
			ResultList<DivisionVo> respResult = new ResultList<>();
			respResult.setRecords(respList);
			return ok(respResult);
		}
		return ok(result.getData());
	}

	private void checkParam(String pattern, String param, String message) {
		boolean isMatch = java.util.regex.Pattern.matches(pattern, param);
		if (!isMatch) {
			throw new BusinessException(ResultCode.INTERNAL_SERVER_ERROR.getCode(), message);
		}
	}

	private DivisionVo convertFromDivisionDto(DivisionDto divisionDto) {
		DivisionVo divisionVo = new DivisionVo();
		divisionVo.setId(divisionDto.getId());
		divisionVo.setDivisionId(divisionDto.getDivisionId());
		divisionVo.setDivisionCode(divisionDto.getDivisionCode());
		divisionVo.setDivisionName(divisionDto.getDivisionName());
		divisionVo.setDivisionType(divisionDto.getDivisionType());
		divisionVo.setCountryCode(divisionDto.getCountryCode());
		divisionVo.setParentId(divisionDto.getParentId());
		divisionVo.setParentName(divisionDto.getParentName());
		divisionVo.setCreatedAt(divisionDto.getCreatedAt());
		divisionVo.setUpdatedAt(divisionDto.getUpdatedAt());
		return divisionVo;
	}
}
