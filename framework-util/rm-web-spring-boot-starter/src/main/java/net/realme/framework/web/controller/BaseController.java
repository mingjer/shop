package net.realme.framework.web.controller;

import net.realme.framework.i18n.MessageSourceService;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultCode;
import net.realme.framework.util.dto.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.common
 *
 * @author 91000044
 * @date 2018/7/24 20:55
 */
public abstract class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected MessageSourceService messageSourceService;

    protected String getMsgByCode(String code) {
        return messageSourceService.getMsgByCode(code);
    }


    protected Result ok() {
        return ResultUtil.success();
    }

    protected Result ok(Object o) {
        return ResultUtil.success(o);
    }

    protected Result err(String errMsg) {
        return ResultUtil.fail(errMsg);
    }

    protected Result err(int resultCode, String errMsg) {
        return ResultUtil.fail(resultCode, errMsg);
    }

    protected Result err(ResultCode resultCode, Object o) {
        return ResultUtil.fail(resultCode,o);
    }

    protected Result err(ResultCode resultCode, String errMsg) {
        return ResultUtil.fail(resultCode, errMsg);
    }

    protected Result errI18N(String errMsg) {
        return ResultUtil.fail(getMsgByCode(errMsg));
    }

    protected Result errI18intN(ResultCode resultCode, String errMsg) {
        return ResultUtil.fail(resultCode, getMsgByCode(errMsg));
    }

    protected Result errI18N(int resultCode, String errMsg) {
        return ResultUtil.fail(resultCode, getMsgByCode(errMsg));
    }
}
