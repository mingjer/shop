package net.realme.mall.store.notification.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.framework.web.controller.LocalizeController;
import net.realme.mall.basics.third.facade.AwsNotifyService;
import net.realme.mall.user.domain.ReserveInfoDto;
import net.realme.mall.user.facade.ReserveService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ReserveController extends LocalizeController {

    @Autowired
    private ReserveService reserveService;

    @Autowired
    private AwsNotifyService awsNotifyService;

    @ApiOperation(value = "预约登记")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phoneNumber", value = "用户手机号", paramType = "form", required = true),
            @ApiImplicitParam(name = "email", value = "用户邮箱", paramType = "form", required = false),
            @ApiImplicitParam(name = "productNum", value = "预约商品数量", paramType = "form", required = true),
            @ApiImplicitParam(name = "skuId", value = "商品Sku编号", paramType = "form", required = true),
    })
    @PostMapping("/reserve/add")
    public Result addReserveInfo(HttpServletRequest httpServletRequest, @NotBlank String phoneNumber, @Email String email,
                                 @Min(1) int productNum,@Min(1) int skuId) {
        if (StringUtils.isBlank(phoneNumber)) {
            return errI18N("err.phone.number.empty");
        }

        ReserveInfoDto reserveInfoDto = new ReserveInfoDto();
        Long currentTime = System.currentTimeMillis() / 1000;
        reserveInfoDto.setPhone(phoneNumber);
        reserveInfoDto.setEmail(email);
        reserveInfoDto.setProductNum(productNum);
        reserveInfoDto.setSkuId(skuId);
        reserveInfoDto.setCreateTime(currentTime);
        reserveInfoDto.setUpdateTime(currentTime);

        ResultT<Long> ret = reserveService.addReserveInfo(reserveInfoDto);

        if (!ret.isSuccess()) {
            return errI18N("err.operation.fail");
        }
        return ok(ret);
    }

    @ApiOperation(value = "获取已预约的用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "商品的SkuId", paramType = "query", required = true),
            @ApiImplicitParam(name = "page", value = "页数", paramType = "query",  defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "页大小", paramType = "query", defaultValue = "20"),
    })
    @GetMapping("/reserve/list")
    public Result getReserveList(@Min(1) int skuId,@Min(1) int page, @Max(1000) int limit) {
        ResultT<ResultList<ReserveInfoDto>> list = reserveService.getReserveInfos(skuId,page,limit);
        return ok(list.getData());
    }

    @ApiOperation(value = "通知已预约的用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "商品的SkuId", paramType = "query", required = true),
    })
    @PostMapping("/reserve/notify")
    public Result notifyUser(Integer skuId) {
        // 判断参数非法
        if(skuId == null || skuId==0){
            return errI18N("err.entity.not.found");
        }
        // 查询推送消息的设备

        // 将用户手机号放到线程池内进行推送

        return ok();
    }
}
