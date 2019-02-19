package net.realme.mall.store.basics.controller;

import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.Pattern;

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
import net.realme.mall.basics.dto.ServiceSiteDto;
import net.realme.mall.basics.facade.ServiceSiteService;
import net.realme.mall.store.basics.resp.ServiceSiteResp;

@Api(tags = { "服务网点相关" })
@Validated
@RestController
public class ServiceSiteController extends LocalizeController {

	@Autowired
	private ServiceSiteService serviceSiteService;

	@ApiOperation(value = "查询服务网点列表", notes = "根据国家，省份，城市，查询服务网点列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "siteCode", value = "站点编号", paramType = "path", dataType = "String", required = true),
			@ApiImplicitParam(name = "provinceName", value = "省份名称", paramType = "query", dataType = "String", required = true),
			@ApiImplicitParam(name = "cityName", value = "城市名称", paramType = "query", dataType = "String", required = true),
			@ApiImplicitParam(name = "page", value = "分页数", paramType = "query", dataType = "int", required = false, defaultValue = "1"),
			@ApiImplicitParam(name = "limit", value = "每页记录数", paramType = "query", dataType = "int", required = false, defaultValue = "10") })
	@GetMapping("service/site/list")
	public Result serviceSiteList(@PathVariable String siteCode,
			@Pattern(regexp = "^.{1,50}$", message = "{province.name.wrong.format}") String provinceName,
			@Pattern(regexp = "^.{1,50}$", message = "{city.name.wrong.format}") String cityName,
			@RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "20") String limit) {
		this.checkParam("^\\d{1,10}$", page, "page wrong format");
		this.checkParam("^\\d{1,10}$", limit, "limit wrong format");
		ResultT<ResultList<ServiceSiteDto>> result = serviceSiteService.list(siteCode.toUpperCase(), provinceName,
				cityName);
		if (result.getData() != null && result.getData().getRecords() != null
				&& !result.getData().getRecords().isEmpty()) {
			List<ServiceSiteResp> respList = new LinkedList<>();
			List<ServiceSiteDto> list = result.getData().getRecords();
			for (ServiceSiteDto serviceSiteDto : list) {
				ServiceSiteResp resp = this.convertFromServiceSiteDto(serviceSiteDto);
				respList.add(resp);
			}
			ResultList<ServiceSiteResp> respResult = new ResultList<>();
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

	private ServiceSiteResp convertFromServiceSiteDto(ServiceSiteDto serviceSiteDto) {
		ServiceSiteResp resp = new ServiceSiteResp();
		resp.setId(serviceSiteDto.getId().toString());
		resp.setName(serviceSiteDto.getName());
		resp.setCountryId(serviceSiteDto.getCountryId());
		resp.setCountryName(serviceSiteDto.getCountryName());
		resp.setProvinceId(serviceSiteDto.getProvinceId());
		resp.setProvinceName(serviceSiteDto.getProvinceName());
		resp.setCityId(serviceSiteDto.getCityId());
		resp.setCityName(serviceSiteDto.getCityName());
		resp.setSiteCode(serviceSiteDto.getSiteCode());
		resp.setPostCode(serviceSiteDto.getPostCode());
		resp.setAddress(serviceSiteDto.getAddress());
		resp.setLatitude(serviceSiteDto.getLatitude());
		resp.setLongitude(serviceSiteDto.getLongitude());
		resp.setPhoneNumber(serviceSiteDto.getPhoneNumber());
		resp.setOpenTimeWeek(serviceSiteDto.getOpenTimeWeek());
		resp.setOpenTime(serviceSiteDto.getOpenTime());
		resp.setCloseTime(serviceSiteDto.getCloseTime());
		resp.setType(serviceSiteDto.getType());
		resp.setCreatedAt(serviceSiteDto.getCreatedAt());
		resp.setUpdatedAt(serviceSiteDto.getUpdatedAt());
		return resp;
	}
}
