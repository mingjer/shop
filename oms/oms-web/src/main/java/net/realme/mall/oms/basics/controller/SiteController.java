package net.realme.mall.oms.basics.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.realme.framework.util.constant.CommonStatus;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.web.controller.BaseController;
import net.realme.mall.basics.dto.SiteDto;
import net.realme.mall.basics.dto.SiteEnum;
import net.realme.mall.basics.facade.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;


/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.basics.controller
 *
 * @author zhangyan
 * @date 2018/7/24 10:45
 */
@Api(tags = {"站点管理"})
@Validated
@RestController
@RequestMapping("/site")
public class SiteController extends BaseController {

    @Autowired
    private SiteService siteService;


    @ApiOperation(value = "添加站点")
    @PostMapping("/create")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", dataType="String", name = "siteCode", value = "站点唯一标识", required = true),
            @ApiImplicitParam(paramType = "form", dataType="string", name = "currencyAbbr", value = "关联货币唯一标识", required = true),
            @ApiImplicitParam(paramType = "form", dataType="string", name = "region", value = "分类", required = true),
            @ApiImplicitParam(paramType = "form", dataType="string", name = "icon", value = "图标"),
            @ApiImplicitParam(paramType = "form", dataType="string", name = "country", value = "国家名称", required = true),
            @ApiImplicitParam(paramType = "form", dataType="string", name = "countryCode", value = "国家编码", required = true),
            @ApiImplicitParam(paramType = "form", dataType="string", name = "language", value = "语言名称", required = true),
    })
    public Result create(@NotEmpty String siteCode,@NotEmpty String currencyAbbr,@NotEmpty String region,String icon, String country, String countryCode, String language) {
        SiteDto siteDto = new SiteDto();
        siteDto.setSiteCode(siteCode);
        siteDto.setCurrencyAbbr(currencyAbbr);
        siteDto.setRegion(region);
        siteDto.setIcon(icon);
        siteDto.setCountry(country);
        siteDto.setLanguage(language);
        siteDto.setCountryCode(countryCode);
        siteDto.setStatus(CommonStatus.ENABLED);
        siteDto.setCreatedAt(System.currentTimeMillis());
        int ret = siteService.addSite(siteDto).getData();
        if (ret > 0) {
            return ok(ret);
        }
        return errI18N("err.create.Site");
    }


    @ApiOperation(value = "更新站点基本信息")
    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", dataType="int", name = "id", value = "站点id", required = true),
            @ApiImplicitParam(paramType = "form", dataType="String", name = "siteCode", value = "站点唯一标识", required = true),
            @ApiImplicitParam(paramType = "form", dataType="string", name = "currencyAbbr", value = "关联货币唯一标识", required = true),
            @ApiImplicitParam(paramType = "form", dataType="string", name = "region", value = "分类", required = true),
            @ApiImplicitParam(paramType = "form", dataType="string", name = "icon", value = "图标"),
            @ApiImplicitParam(paramType = "form", dataType="string", name = "country", value = "国家名称", required = true),
            @ApiImplicitParam(paramType = "form", dataType="string", name = "language", value = "语言名称", required = true),
    })
    public Result update(@Min(1) int id, @NotEmpty String siteCode,@NotEmpty String currencyAbbr,
                         @NotEmpty String region,String icon, String country, String language) {
        SiteDto siteDto = siteService.getById(id).getData();
        if (siteDto == null) {
            return errI18N("err.entity.not.found");
        }
        //全球站不允许修改
        if (SiteEnum.GLOBAL_SITE.getValue().equals(siteDto.getSiteCode())) {
            return errI18N("err.edit.entity.not.allowed");
        }
        siteDto.setSiteCode(siteCode);
        siteDto.setCurrencyAbbr(currencyAbbr);
        siteDto.setRegion(region);
        siteDto.setIcon(icon);
        siteDto.setCountry(country);
        siteDto.setLanguage(language);
        siteDto.setUpdatedAt(System.currentTimeMillis());
        int ret = siteService.updateSite(siteDto).getData();
        if (ret > 0) {
            return ok(ret);
        }
        return errI18N("err.update.Site");
    }



    @ApiOperation(value = "获取站点信息")
    @GetMapping("/get")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "站点ID", required = true),
    })
    public Result get(@Min(1) int id) {
        SiteDto siteDto = siteService.getById(id).getData();
        return ok(siteDto);
    }

    @ApiOperation(value = "更新站点的状态")
    @PostMapping("/status")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "id", value = "站点ID", required = true),
            @ApiImplicitParam(paramType = "form", name = "status", value = "站点的开关", required = true),
    })
    public Result status(@Min(1) int id, byte status) {
        SiteDto siteDto = new SiteDto();
        siteDto.setStatus(status);
        siteDto.setId(id);
        siteDto.setUpdatedAt(System.currentTimeMillis());
        int ret = siteService.updateSiteStatus(siteDto).getData();
        if (ret > 0) {
            return ok(ret);
        }
        return errI18N("err.status.Site");

    }

    @ApiOperation(value = "站点列表")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int",value = "页面",name = "page", defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "页大小", paramType = "query", defaultValue = "20"),
    })
    public Result list(int page, int limit) {
        ResultList<SiteDto> list = siteService.listAll(page,limit).getData();
        return ok(list);
    }

    @ApiOperation(value = "SKU中获取站点列表")
    @GetMapping("/sku_list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", value = "页面", name = "page", defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "页大小", paramType = "query", defaultValue = "20"),
    })
    public Result sku_list(int page, int limit) {
        ResultList<SiteDto> list = siteService.list(page, limit).getData();
        return ok(list);
    }


}










