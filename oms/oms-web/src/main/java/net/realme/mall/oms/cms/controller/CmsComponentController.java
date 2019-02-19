package net.realme.mall.oms.cms.controller;

import io.swagger.annotations.*;
import net.realme.framework.util.constant.CommonStatus;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.web.controller.BaseController;
import net.realme.mall.basics.dto.SiteEnum;
import net.realme.mall.oms.cms.facade.CmsComponentService;
import net.realme.mall.oms.cms.req.ComponentsReq;
import net.realme.mall.oms.domain.cms.CmsComponentDto;
import net.realme.mall.oms.domain.cms.CmsComponentsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.cms.controller
 *
 * @author 91000044
 * @date 2018/7/28 18:21
 */
@Api(tags = {"页面组件管理"})
@Validated
@RestController
@RequestMapping("/cms/component")
public class CmsComponentController extends BaseController {

    @Autowired
    private CmsComponentService cmsComponentService;

    @ApiOperation(value = "添加默认组件")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "type", value = "组件类型", required = true),
            @ApiImplicitParam(paramType = "form", name = "name", value = "组件名称", required = true),
            @ApiImplicitParam(paramType = "form", name = "content", value = "组件内容", required = true),
    })
    @PostMapping("/create")
    public Result create(@NotBlank String type, @NotBlank String name, @NotBlank String content) {
        if (cmsComponentService.isTypeNameExists(type, name)) {
            return errI18N("err.entity.duplicated");
        }
        CmsComponentDto cmsComponentDto = new CmsComponentDto();
        cmsComponentDto.setSiteCodes(SiteEnum.DEFAULT_SITE.getValue());
        cmsComponentDto.setType(type);
        cmsComponentDto.setName(name);
        cmsComponentDto.setContent(content);
        cmsComponentDto.setStatus(CommonStatus.ENABLED);
        cmsComponentDto.setIsDefault(CommonStatus.YES);
        long now = System.currentTimeMillis();
        cmsComponentDto.setCreatedAt(now);
        cmsComponentDto.setUpdatedAt(now);
        int ret = cmsComponentService.add(cmsComponentDto);
        if (ret > 0) {
            return ok(ret);
        }
        return errI18N("err.create.cms.component");
    }

    @ApiOperation(value = "默认组件更新")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "id", value = "组件ID", required = true),
            @ApiImplicitParam(paramType = "form", name = "name", value = "组件名称", required = true),
            @ApiImplicitParam(paramType = "form", name = "content", value = "组件内容", required = true),
    })
    @PostMapping("/update")
    public Result update(@Min(1) int id, @NotBlank String name, @NotBlank String content) {
        CmsComponentDto cmsComponentDto = cmsComponentService.get(id);
        if (cmsComponentDto == null) {
            return errI18N("err.entity.not.found");
        }
        if (!cmsComponentDto.getName().equals(name) && cmsComponentService.isTypeNameExists(cmsComponentDto.getType(), name)) {
            return errI18N("err.entity.duplicated");
        }
        CmsComponentDto updatedDto = new CmsComponentDto();
        updatedDto.setName(name);
        updatedDto.setContent(content);
        int ret = cmsComponentService.updateDefault(cmsComponentDto, updatedDto);
        if (ret > 0) {
            return ok(ret);
        }
        return errI18N("err.update.cms.component");
    }

    @ApiOperation(value = "本地化更新")
    @PostMapping("/updateL10N")
    public Result updateL10N(@Validated @ApiParam(value = "本地化配置", required = true) @RequestBody ComponentsReq req) {
        String type = req.getType();
        String name = req.getName();
        List<CmsComponentDto> cmsComponentDtos = new ArrayList<>(50);
        long now = System.currentTimeMillis();
        for (ComponentsReq.ComponentL10N componentL10N: req.getLocalization()) {
            CmsComponentDto cmsComponentDto = new CmsComponentDto();
            cmsComponentDto.setId(componentL10N.getId());
            cmsComponentDto.setStatus(CommonStatus.ENABLED);
            cmsComponentDto.setIsDefault(CommonStatus.NO);
            cmsComponentDto.setType(type);
            cmsComponentDto.setName(name);
            cmsComponentDto.setContent(componentL10N.getContent());
            cmsComponentDto.setSiteCodes(componentL10N.getSiteCodes());
            cmsComponentDto.setCreatedAt(now);
            cmsComponentDto.setUpdatedAt(now);
            cmsComponentDtos.add(cmsComponentDto);
        }

        int ret = cmsComponentService.updateL10NByName(type, name, cmsComponentDtos);
        if (ret > 0 ) {
            return ok(ret);
        }
        return errI18N("err.update.cms.component.l10n");
    }

    @ApiOperation(value = "更改组件状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "id", value = "组件ID", required = true),
            @ApiImplicitParam(paramType = "form", name = "status", value = "组件状态", required = true),
    })
    @PostMapping("/status")
    public Result status(@Min(1) int id, byte status) {
        int ret = cmsComponentService.updateStatus(id, status);
        if (ret > 0) {
            return ok(ret);
        }
        return errI18N("err.update.cms.component");
    }

    @ApiOperation(value = "查询组件")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "组件ID", required = true),
    })
    @GetMapping("/get")
    public Result get(@Min(1) int id) {
        CmsComponentDto cmsComponentDto = cmsComponentService.get(id);
        if (cmsComponentDto != null) {
            return ok(cmsComponentDto);
        }
        return ok();
    }

    @ApiOperation(value = "组件默认值列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "type", value = "组件类型, [sec|var]", required = true),
            @ApiImplicitParam(paramType = "query", name = "page", value = "页数", required = true, defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "页大小", required = true, defaultValue = "20"),
    })
    @GetMapping("/listByType")
    public Result listByType(@NotBlank String type, @Min(1) int page, @Max(1000) int limit) {
        CmsComponentsQuery query = new CmsComponentsQuery();
        query.setType(type);
        query.setPage(page);
        query.setLimit(limit);
        ResultList<CmsComponentDto> data = cmsComponentService.defaultList(query);
        return ok(data);
    }


    @ApiOperation(value = "同类型组件列表，即L10N")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "type", value = "组件类型, [sec|var]", required = true),
            @ApiImplicitParam(paramType = "query", name = "name", value = "组件名称", required = true),
    })
    @GetMapping("/listByName")
    public Result listByName(@NotBlank String type, @NotBlank String name) {
        CmsComponentsQuery query = new CmsComponentsQuery();
        query.setType(type);
        query.setName(name);
        ResultList<CmsComponentDto> data = cmsComponentService.listByName(query);
        return ok(data);
    }

}
