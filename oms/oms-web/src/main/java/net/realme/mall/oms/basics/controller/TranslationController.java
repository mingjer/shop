package net.realme.mall.oms.basics.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.*;
import net.realme.framework.util.constant.CommonStatus;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.web.controller.BaseController;
import net.realme.mall.basics.dto.SiteEnum;
import net.realme.mall.basics.dto.TranslationDto;
import net.realme.mall.basics.dto.TranslationQuery;
import net.realme.mall.basics.facade.TranslationService;
import net.realme.mall.oms.basics.req.TranslationL10NReq;
import net.realme.mall.oms.basics.resp.TranslationL10NResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.basics.controller
 *
 * @author zhangyan
 * @date 2018/7/27 17:50
 */
@Api(tags = {"翻译管理"})
@Validated
@RestController
@RequestMapping("/translation")
public class TranslationController extends BaseController {

    @Autowired
    private TranslationService translationService;

    @ApiOperation(value = "添加默认t9n翻译")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "t9nKey", value = "翻译字段", required = true),
            @ApiImplicitParam(paramType = "form", name = "t9nValue", value = "翻译值", required = true),
    })
    @PostMapping("/create")
    public Result create(@NotBlank String t9nKey, @NotBlank String t9nValue) {
        TranslationDto translationDto = new TranslationDto();
        translationDto.setSiteCode(SiteEnum.DEFAULT_SITE.getValue());
        translationDto.setT9nValue(t9nValue);
        translationDto.setT9nKey(t9nKey);
        translationDto.setCreatedAt(System.currentTimeMillis());
        int ret = translationService.translationAdd(translationDto).getData();
        if (ret > 0) {
            return ok(ret);
        }
        return errI18N("err.update.translation");
    }

    @ApiOperation(value = "增加翻译词条多个value值")
    @PostMapping("/updateL10N")
    public Result updateL10N(@Validated @ApiParam(value = "增加翻译对象value值及多个站点", required = true) @RequestBody TranslationL10NReq req) {
        String localT9nKey = req.getT9nKey();
        TranslationQuery translationQuery = new TranslationQuery();
        translationQuery.setT9nKey(localT9nKey);
        List<TranslationDto> translationDtos = new ArrayList<>();
        for (TranslationL10NReq.TranslationL10N translationL10N : req.getTranslationL10N()) {
            String[] siteCodeArr = translationL10N.getSiteCodes().split(",");
            for (String siteCode : siteCodeArr) {
                TranslationDto translationDto = new TranslationDto();
                translationDto.setSiteCode(siteCode);
                translationDto.setT9nValue(translationL10N.getT9nValue());
                translationDto.setT9nKey(localT9nKey);
                translationDto.setCreatedAt(System.currentTimeMillis());
                translationDto.setStatus(CommonStatus.ENABLED);
                translationDtos.add(translationDto);
            }
        }
        translationService.deleteBySiteCode(translationQuery);
        int ret = translationService.batchInsert(translationDtos).getData();
        if (ret > 0) {
            return ok(ret);
        }
        return errI18N("err.update.translation");
    }

    @ApiOperation(value = "更新翻译基本信息")
    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", dataType = "int", name = "id", value = "翻译ID", required = true),
            @ApiImplicitParam(paramType = "form", dataType = "string", name = "t9nKey", value = "t9n字段"),
            @ApiImplicitParam(paramType = "form", dataType = "string", name = "t9nValue", value = "t9n翻译值"),
    })
    public Result update(@Min(1) int id, String t9nKey, String t9nValue) {
        TranslationDto translationDto = translationService.getById(id).getData();
        if (translationDto == null) {
            return errI18N("err.entity.not.found");
        }
        translationDto.setT9nKey(t9nKey);
        translationDto.setT9nValue(t9nValue);
        translationDto.setUpdatedAt(System.currentTimeMillis());
        int ret = translationService.updateTranslation(translationDto).getData();
        if (ret > 0) {
            return ok(ret);
        }
        return errI18N("err.update.translation");
    }

    @ApiOperation(value = "获取翻译信息")
    @GetMapping("/get")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "翻译id", required = true),
    })
    public Result get(@Min(1) int id) {
        TranslationDto translationDto = translationService.getById(id).getData();
        return ok(translationDto);
    }

    @ApiOperation(value = "更新翻译的状态")
    @PostMapping("/status")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "id", value = "翻译ID", required = true),
            @ApiImplicitParam(paramType = "form", name = "status", value = "翻译的开关", required = true),
    })
    public Result status(@Min(1) int id, byte status) {
        TranslationDto translationDto = new TranslationDto();
        translationDto.setStatus(status);
        translationDto.setId(id);
        translationDto.setUpdatedAt(System.currentTimeMillis());
        int ret = translationService.updateTranslationStatus(translationDto).getData();
        if (ret > 0) {
            return ok(ret);
        }
        return errI18N("err.status.translation");
    }

    @ApiOperation(value = "翻译列表")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "int", value = "页面", name = "page", defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "页大小", paramType = "query", defaultValue = "20"),
    })
    public Result list(int page, int limit) {
        TranslationQuery translationQuery = new TranslationQuery();
        translationQuery.setPage(page);
        translationQuery.setLimit(limit);
        ResultList<TranslationDto> list = translationService.listByDefault(translationQuery).getData();
        return ok(list);
    }

    @ApiOperation(value = "所有本地化词条列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "t9nKey", value = "翻译字段", required = true),
    })
    @GetMapping("/listByName")
    public Result listByName(@NotBlank String t9nKey) {
        ResultList<TranslationL10NResp> data = new ResultList<TranslationL10NResp>();
        TranslationQuery translationQuery = new TranslationQuery();
        translationQuery.setT9nKey(t9nKey);
        ResultList<TranslationDto> rawData = translationService.listByKey(t9nKey).getData();
        List<TranslationDto> translation = rawData.getRecords();
        if (translation == null || translation.isEmpty()) {
            return ok(data);
        }
        HashMap<String, TranslationL10NResp> result = new HashMap<>(50);
        for (TranslationDto translationDto : translation) {
            String t9nValue = translationDto.getT9nValue();
            if (!result.containsKey(t9nValue)) {
                TranslationL10NResp translationL10NResp = new TranslationL10NResp(t9nKey, t9nValue);
                String siteCode = translationDto.getSiteCode();
                translationL10NResp.getSiteCodesArr().add(siteCode);
                result.put(t9nValue, translationL10NResp);
            } else {
                result.get(t9nValue).getSiteCodesArr().add(translationDto.getSiteCode());
            }
        }
        List<TranslationL10NResp> list = new ArrayList<>(50);
        for (String key : result.keySet()) {
            TranslationL10NResp translationL10NResp = result.get(key);
            list.add(translationL10NResp);
        }
        data.setRecords(list);
        PageInfo<TranslationL10NResp> pageInfo = new PageInfo<>(list);
        data.setPageSize(Math.toIntExact(pageInfo.getTotal()));
        data.setRecordTotal(pageInfo.getTotal());

        return ok(data);
    }

    @ApiOperation(value = "根据siteCode和t9nKey值查找对应的数据")
    @PostMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", dataType = "string", name = "t9nKey", value = "t9n字段"),
            @ApiImplicitParam(paramType = "form", dataType = "string", name = "siteCode", value = "t9n翻译值"),
    })
    public Result delete(String t9nKey) {
        TranslationQuery translationQuery = new TranslationQuery();
        translationQuery.setT9nKey(t9nKey);
        int ret = translationService.delete(translationQuery).getData();
        if (ret > 0) {
            return ok(ret);
        }
        return errI18N("err.delete.translation");
    }

}