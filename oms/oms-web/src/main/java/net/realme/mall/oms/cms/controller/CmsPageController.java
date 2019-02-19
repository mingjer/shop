package net.realme.mall.oms.cms.controller;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.cms.controller
 *
 * @author 91000044
 * @date 2018/7/31 15:25
 */

import freemarker.template.TemplateException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.realme.framework.util.constant.CommonStatus;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.web.controller.BaseController;
import net.realme.mall.oms.cms.facade.CmsPageService;
import net.realme.mall.oms.cms.ftl.CmsPageEngine;
import net.realme.mall.oms.domain.cms.CmsPageDto;
import net.realme.mall.oms.domain.cms.CmsPageQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.*;
import java.io.IOException;
import java.nio.channels.OverlappingFileLockException;

@Api(tags = {"页面管理"})
@Validated
@RestController
@RequestMapping("/cms/page")
public class CmsPageController extends BaseController {

    @Autowired
    private CmsPageEngine cmsPageEngine;

    @Autowired
    private CmsPageService cmsPageService;


    @ApiOperation(value = "添加页面")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "name", value = "页面名称", required = true),
            @ApiImplicitParam(paramType = "form", name = "uri", value = "页面URI, 必须以/开头", required = true),
            @ApiImplicitParam(paramType = "form", name = "siteCodes", value = "站点", required = true),
            @ApiImplicitParam(paramType = "form", name = "content", value = "页面内容", required = true),
            @ApiImplicitParam(paramType = "form", name = "description", value = "页面描述", required = false),
            @ApiImplicitParam(paramType = "form", name = "domainType", value = "发布到的域名类别1：www,2:buy，默认发布到www域名下", required = true),
    })
    @PostMapping("/create")
    public Result create(@NotBlank String name, @NotNull @Pattern(regexp = "^/.*") String uri,
                         @NotBlank String siteCodes, @NotBlank String content, String description, Byte domainType) {
        CmsPageDto cmsPageDto = new CmsPageDto();
        cmsPageDto.setName(name);
        cmsPageDto.setDescription(description);
        cmsPageDto.setUri(uri);
        cmsPageDto.setSiteCodes(siteCodes);
        cmsPageDto.setContent(content);
        cmsPageDto.setDomainType(domainType);
        cmsPageDto.setStatus(CommonStatus.ENABLED);
        long now = System.currentTimeMillis();
        cmsPageDto.setCreatedAt(now);
        cmsPageDto.setUpdatedAt(now);
        int ret = cmsPageService.add(cmsPageDto);
        if (ret > 0) {
            return ok(ret);
        }
        return errI18N("err.create.cms.page");
    }

    @ApiOperation(value = "更新页面")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "id", value = "页面ID", required = true),
            @ApiImplicitParam(paramType = "form", name = "name", value = "页面名称", required = true),
            @ApiImplicitParam(paramType = "form", name = "uri", value = "页面URI, 必须以/开头", required = true),
            @ApiImplicitParam(paramType = "form", name = "siteCodes", value = "站点", required = true),
            @ApiImplicitParam(paramType = "form", name = "content", value = "页面内容", required = true),
            @ApiImplicitParam(paramType = "form", name = "description", value = "页面描述", required = false),
            @ApiImplicitParam(paramType = "form", name = "domainType", value = "发布到的域名类别1：www,2:buy，默认发布到www域名下", required = true),
    })
    @PostMapping("/update")
    public Result update(@Min(1) int id, @NotBlank String name, @NotNull @Pattern(regexp = "^/.*") String uri,
                         @NotBlank String siteCodes, @NotBlank String content, String description, Byte domainType) {
        CmsPageDto cmsPageDto = cmsPageService.get(id);
        if (cmsPageDto == null) {
            return errI18N("err.entity.not.found");
        }
        cmsPageDto.setUri(uri);
        cmsPageDto.setName(name);
        cmsPageDto.setDescription(description);
        cmsPageDto.setContent(content);
        cmsPageDto.setSiteCodes(siteCodes);
        cmsPageDto.setDomainType(domainType);
        cmsPageDto.setUpdatedAt(System.currentTimeMillis());
        int ret = cmsPageService.update(cmsPageDto);
        if (ret > 0) {
            return ok(ret);
        }
        return errI18N("err.update.cms.page");
    }

    @ApiOperation(value = "修改页面状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "id", value = "页面ID", required = true),
            @ApiImplicitParam(paramType = "form", name = "status", value = "状态值", required = true),
    })
    @PostMapping("/status")
    public Result status(int id,  byte status) {
        int ret = cmsPageService.updateStatus(id, status);
        if (ret > 0) {
            return ok(ret);
        }
        return errI18N("err.update.cms.page");
    }

    @ApiOperation(value = "页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "page", value = "页数", defaultValue = "1"),
            @ApiImplicitParam(paramType = "query", name = "limit", value = "页大小", defaultValue = "20"),
    })
    @GetMapping("/list")
    public Result list(@Min(1) int page, @Max(1000) int limit) {
        CmsPageQuery cmsPageQuery = new CmsPageQuery();
        cmsPageQuery.setPage(page);
        cmsPageQuery.setLimit(limit);
        ResultList<CmsPageDto> list = cmsPageService.list(cmsPageQuery);
        return ok(list);
    }


    @ApiOperation(value = "获取页面")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "页面ID"),
    })
    @GetMapping("/get")
    public Result get(@Min(1) int id) {
        return ok(cmsPageService.get(id));
    }

    @ApiOperation(value = "预览页面")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "页面ID", required = true),
            @ApiImplicitParam(paramType = "query", name = "siteCode", value = "站点ID", required = true),
    })
    @ResponseBody
    @GetMapping("/preview")
    public Object preview(@Min(1) int id, @NotBlank String siteCode) throws IOException {
        CmsPageDto cmsPageDto = cmsPageService.get(id);
        try {
            return cmsPageEngine.render(cmsPageDto, siteCode);
        } catch (TemplateException e) {
            return "err.preview.cms.page";
        }
    }

    @ApiOperation(value = "发布页面")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "id", value = "页面ID", required = true),
            @ApiImplicitParam(paramType = "form", name = "siteCode", value = "站点ID，不传则表示该页面所有站点都发布"),
    })
    @PostMapping("/release")
    public Result release(@Min(1) int id, String siteCode) {
        long version = System.currentTimeMillis();
        CmsPageDto cmsPageDto = cmsPageService.get(id);
        try{
            for (String sc : cmsPageDto.getSiteCodesArr()) {
                if (StringUtils.isBlank(siteCode) || sc.equals(siteCode)) {
                    cmsPageService.requestToRelease(id, sc, version);
                    String content = cmsPageEngine.generate(cmsPageDto, sc, cmsPageDto.getDomainType());
                    cmsPageService.releaseDone(id, sc, version, content);
                }
            }
            return ok();
        } catch (OverlappingFileLockException e) {
            logger.error("page release failed. ", e);
            return errI18N("msg.retry.generate.cms.page");
        } catch (Exception e) {
            logger.error("page release failed. ", e);
            return err(e.getMessage());
        }
    }
}
