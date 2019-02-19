package net.realme.mall.store.notification.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultT;
import net.realme.framework.util.text.RegexUtil;
import net.realme.framework.web.controller.LocalizeController;
import net.realme.framework.web.util.RequestHelper;
import net.realme.mall.basics.dto.ServiceSiteDto;
import net.realme.mall.basics.facade.ServiceSiteService;
import net.realme.mall.store.common.consts.NotificationConst;
import net.realme.mall.user.facade.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import static net.realme.mall.store.common.consts.NotificationConst.*;
import static net.realme.mall.store.common.consts.NotificationConst.RetCode.*;

/**
 * Created by 91000156 on 2018/9/10 20:07
 */
@Api(tags = {"消息通知"})
@Validated
@RestController
@RequestMapping("/notification")
public class NotificationController extends LocalizeController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ServiceSiteService serviceSiteService;

    @ApiOperation(value = "发送营业网点短信")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "number", value = "手机号码", paramType = "form", required = true),
            @ApiImplicitParam(name = "serviceSiteId", value = "营业网点ID", paramType = "form", required = true),
    })
    @PostMapping("/send")
    public Result publishToEmail(HttpServletRequest httpServletRequest, @NotBlank String number, @Min(1) Integer serviceSiteId) {
        // 获取Http请求的IP地址
        String clientIp = RequestHelper.getIpAddress(httpServletRequest);
        if (!RegexUtil.isInternationalPhone(number) || notificationService.isIllegalAttack(number, clientIp)) {
            // 验证不通过，则直接返回
            return errI18N(VALIDATE_FAILED, VALIDATE_FAILED_MSG);
        }
        String msgId = "";
        try {
            // 获取对应的营业网点地址
            ResultT<ServiceSiteDto> serviceSiteResult = serviceSiteService.getServiceSiteById(serviceSiteId);
            // 不成功，则直接返回
            if (serviceSiteResult == null || !serviceSiteResult.isSuccess()) {
                return errI18N(SERVICE_SITE_NONE, SERVICE_SITE_NONE_MSG);
            }
            // 获取营业网点的地址
            String content = serviceSiteResult.getData().getAddress();
            msgId = notificationService.sendAddressSMSToUser(number, content).getData();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(EXCEPTION_ERROR, EXCEPTION_ERROR_MSG);
        }
        // 是否有发送ID
        if (msgId.isEmpty()) {
            return errI18N(SEND_MSG_FAILED, SEND_MSG_FAILED_MSG);
        }
        return ok(msgId);
    }

    @ApiOperation(value = "消息订阅", notes = "官网首页订阅和商品预约订阅所使用的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "订阅者的邮箱", paramType = "form", required = true),
    })
    @PostMapping("/index/subscribe")
    public Result subscribeMessage(HttpServletRequest httpServletRequest, @NotBlank @Email String email) {
        // 校验订阅邮箱是否合法
        if (!RegexUtil.isEmail(email)) {
            // 验证不通过，则直接返回
            return errI18N(VALIDATE_FAILED, VALIDATE_FAILED_MSG);
        }
        ResultT<String> result = null;
        try {
            // 获取请求IP
            String clientIp = RequestHelper.getIpAddress(httpServletRequest);
            // 检查请求是否正常
            ResultT<Boolean> checkResult = notificationService.isIllegalSubAttack(clientIp);
            if (!checkResult.isSuccess()) {
                return errI18N(VALIDATE_FAILED, EXCEPTION_ERROR_MSG);
            }
            result = notificationService.frontPageEmailSub(email);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(EXCEPTION_ERROR, EXCEPTION_ERROR_MSG);
        }
        if (result == null || result.getCode() != 200) {
            return errI18N(SUBSCRIBE_FAILED, SUBSCRIBE_FAILED_MSG);
        }
        return ok();
    }

    @ApiOperation(value = "消息订阅", notes = "官网首页订阅和商品预约订阅所使用的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "商品的skuId", paramType = "form", required = true),
            @ApiImplicitParam(name = "subscriber", value = "订阅者，邮箱或手机", paramType = "form", required = true),
    })
    @PostMapping("/sku/reserve")
    public Result subscribeMessage(HttpServletRequest httpServletRequest, @NotBlank String skuId, @NotBlank String subscriber) {
        // 预约时校验手机号或者邮箱是否合法
        if (!RegexUtil.isInternationalPhone(subscriber) && !RegexUtil.isEmail(subscriber)) {
            // 验证不通过，则直接返回
            return errI18N(VALIDATE_FAILED, VALIDATE_FAILED_MSG);
        }
        ResultT<String> result = null;
        try {
            result = notificationService.productReserveSub(skuId, subscriber);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(EXCEPTION_ERROR, EXCEPTION_ERROR_MSG);
        }
        if (result == null || result.getCode() != 200) {
            return errI18N(SUBSCRIBE_FAILED, SUBSCRIBE_FAILED_MSG);
        }
        return ok();
    }

    @ApiOperation(value = "消息发布", notes = "发布类型 1.官网首页订阅发布 , 2.商品预约订阅发布")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId", value = "商品预约订阅发布时填写", paramType = "form"),
            @ApiImplicitParam(name = "topicType", value = "主题类型", paramType = "form", required = true),
            @ApiImplicitParam(name = "msgContent", value = "发布的消息内容", paramType = "form", required = true),
    })
    @PostMapping("/publish")
    public Result publishMessage(String skuId, @Min(1) Integer topicType, @NotBlank String msgContent) {
        String topic = "";
        if (topicType == NotificationConst.SubType.INDEX_SUB) {
            topic = Topic.INDEX_EMAIL_SUB;
            logger.info("publish front page message to topic : {}", topic);
        } else if (topicType == NotificationConst.SubType.RESERVE_SUB && StringUtil.isNotEmpty(skuId)) {
            topic = String.format(Topic.SKU_OUT_STOCK_NOTIFY, skuId);
            logger.info("publish reserve message to topic : {}", topic);
        } else {
            return errI18N(VALIDATE_FAILED, VALIDATE_FAILED_MSG);
        }
        try {
            ResultT<String> result = notificationService.publishMessageToTopic(topic, msgContent);
            logger.info("publish result is code [{}] msg [{}]", result.getCode(), result.getMsg());
            if (result == null || result.getCode() != 200) {
                return errI18N(result.getCode(), result.getMsg());
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return errI18N(PUBLISH_FAILED, PUBLISH_FAILED_MSG);
        }
        return ok();
    }
}
