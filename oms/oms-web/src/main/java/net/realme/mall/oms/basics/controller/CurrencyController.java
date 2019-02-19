package net.realme.mall.oms.basics.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.realme.framework.util.constant.CommonStatus;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.web.controller.BaseController;
import net.realme.mall.basics.dto.CurrencyDto;
import net.realme.mall.basics.facade.CurrencyService;
import net.realme.mall.oms.common.PaginationReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.basics.controller
 *
 * @author 91000044
 * @date 2018/7/24 20:54
 */
@Api(tags = {"币种管理"})
@Validated
@RestController
@RequestMapping("/currency")
public class CurrencyController extends BaseController {

    @Autowired
    private CurrencyService currencyService;

    @ApiOperation(value = "添加币种")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "name", value = "名称", required = true),
            @ApiImplicitParam(paramType = "form", name = "abbr", value = "缩/简写", required = true),
            @ApiImplicitParam(paramType = "form", name = "symbol", value = "符号", required = true),
            @ApiImplicitParam(paramType = "form", name = "cnName", value = "中文名称"),
    })
    @PostMapping("/create")
    public Result create(@NotBlank String abbr, @NotBlank String name, String symbol, String cnName) {
        CurrencyDto currencyExists = currencyService.getByAbbr(abbr).getData();
        if (currencyExists != null) {
            return errI18N("err.entity.duplicated");
        }
        CurrencyDto currencyDto = new CurrencyDto();
        currencyDto.setAbbr(abbr);
        currencyDto.setName(name);
        currencyDto.setSymbol(symbol);
        currencyDto.setCnName(cnName);
        currencyDto.setStatus(CommonStatus.ENABLED);
        currencyDto.setCreatedAt(System.currentTimeMillis());
        int ret = currencyService.addCurrency(currencyDto).getData();
        if (ret > 0) {
            return ok(ret);
        }
        return errI18N("err.create.currency");
    }

    @ApiOperation(value = "更新币种基本信息")
    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "id", value = "币种ID", required = true),
            @ApiImplicitParam(paramType = "form", name = "name", value = "名称"),
            @ApiImplicitParam(paramType = "form", name = "cnName", value = "中文名称"),
            @ApiImplicitParam(paramType = "form", name = "symbol", value = "符号"),
    })
    public Result update(@Min(1) int id, String name, String symbol, String cnName) {
        CurrencyDto currencyDto = currencyService.getById(id).getData();
        if (currencyDto == null) {
            return errI18N("err.entity.not.found");
        }
        currencyDto.setName(name);
        currencyDto.setSymbol(symbol);
        currencyDto.setCnName(cnName);
        currencyDto.setUpdatedAt(System.currentTimeMillis());
        int ret = currencyService.updateCurrency(currencyDto).getData();
        if (ret > 0) {
            return ok(ret);
        }
        return errI18N("err.update.currency");
    }

    @ApiOperation(value = "更新币种状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "form", name = "id", value = "币种ID", required = true),
            @ApiImplicitParam(paramType = "form", name = "status", value = "状态", required = true),
    })
    @PostMapping("/status")
    public Result status(@Min(1) int id, byte status) {
        CurrencyDto currencyDto = new CurrencyDto();
        currencyDto.setId(id);
        currencyDto.setStatus(status);
        currencyDto.setUpdatedAt(System.currentTimeMillis());
        int ret = currencyService.updateCurrencyStatus(currencyDto).getData();
        if (ret > 0) {
            return ok(ret);
        }
        return errI18N("err.status.currency");
    }

    @ApiOperation(value = "获取币种信息")
    @GetMapping("/get")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "币种ID", required = true),
    })
    public Result get(@Min(1) int id) {
        CurrencyDto currencyDto = currencyService.getById(id).getData();
        return ok(currencyDto);
    }

    @ApiOperation(value = "币种列表")
    @GetMapping("/list")
    public Result list(@Validated PaginationReq req) {
        ResultList<CurrencyDto> list = currencyService.list(req.getPage(), req.getLimit()).getData();
        return ok(list);
    }

}
