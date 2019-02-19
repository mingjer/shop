package net.realme.mall.store.notification.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.realme.framework.async.AsyncExecutorConfig;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultList;
import net.realme.framework.util.dto.ResultT;
import net.realme.framework.web.controller.LocalizeController;
import net.realme.mall.user.domain.SubscribeEmailDto;
import net.realme.mall.user.facade.SubscribeService;
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
import java.util.concurrent.Executor;

/** 
 * @Author: 91000156
 * @Date: 2018/8/24 10:56
 * @Description:
 */
public class EmailController extends LocalizeController {

    @Autowired
    private SubscribeService subscribeService;

    @Autowired
    private AsyncExecutorConfig asyncExecutorConfig;

    // 每次发送邮件的数量
    private static final int SEND_MAIL_BATCH_NUM =2;

    @ApiOperation(value = "邮件订阅")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱", paramType = "query", required = true),
    })
    @GetMapping("/email/subscribe")
    public Result subscribeByEmail(HttpServletRequest httpServletRequest,@Email String email) {
        // 检查参数是否合法
        if(StringUtils.isEmpty(email)){
            return errI18N("err.entity.not.found");
        }
        // 获取请求IP
//        String clientIp = RequestHelper.getIpAddress(httpServletRequest);
        // 检查请求是否正常
//        ResultT<Boolean> checkResult = subscribeService.isIllegalAttack(clientIp);
//        if(!checkResult.isSuccess()){
//            return errI18N("err.illegal.attack");
//        }
        SubscribeEmailDto subscribeEmailDto = new SubscribeEmailDto();
        // 获取当前时间戳
        Long currentTime = System.currentTimeMillis() / 1000;
        subscribeEmailDto.setEmail(email);
        subscribeEmailDto.setCreateTime(currentTime);
        subscribeEmailDto.setUpdateTime(currentTime);
        ResultT<Long> ret = subscribeService.addSubscribeInfo(subscribeEmailDto);
        if(!ret.isSuccess()){
            errI18N("err.operation.fail");
        }
        return ok();
    }

    @ApiOperation(value = "邮件发布")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "emailTitle", value = "邮件标题", paramType = "form", required = true),
            @ApiImplicitParam(name = "emailContent", value = "邮件内容", paramType = "form", required = true),
    })
    @PostMapping("/email/publish")
    public Result publishToEmail(@NotBlank String emailTitle, @NotBlank String emailContent) {
        // 初始化线程池
        Executor executor = null;
        try {
            // 获取待发送的邮箱数量
            ResultT<Integer> countResult = subscribeService.getSubscribeInfoCount();
            // 异常则直接返回
            if (countResult.isSuccess()) {
                return errI18N("err.operation.fail");
            }
            // 总查询次数
            int queryTime = (countResult.getData() / SEND_MAIL_BATCH_NUM) + 1;
            // 创建线程池
            executor = asyncExecutorConfig.getAsyncExecutor();
            // 逐一添加发邮件任务
            for (int i = 1; i <= queryTime; i++) {
                ResultT<ResultList<SubscribeEmailDto>> resultList = subscribeService.getSubscribeInfoList(i, SEND_MAIL_BATCH_NUM);
                // 向线程池中添加任务
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        // 执行发送邮件的任务
                        subscribeService.sendMailByBatch(emailTitle, emailContent, resultList.getData().getRecords());
                    }
                });
            }
        } catch (Exception e) {
            return errI18N("err.operation.fail");
        }
        return ok();
    }

    @ApiOperation(value = "获取已订阅的邮箱")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", paramType = "query",  defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "页大小", paramType = "query", defaultValue = "20"),
    })
    @GetMapping("/list")
    public Result getSubscribeList(@Min(1) int page, @Max(1000) int limit) {
        ResultT<ResultList<SubscribeEmailDto>> list = subscribeService.getSubscribeInfoList(page,limit);
        return ok(list.getData());
    }

    @ApiOperation(value = "取消订阅")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "邮箱地址", paramType = "form", required = true),
    })
    @PostMapping("/email/cancel")
    public Result cancelSubEmail(@NotBlank @Email String email) {
        SubscribeEmailDto subscribeEmailDto = new SubscribeEmailDto();
        subscribeEmailDto.setEmail(email);
        subscribeService.cancelSubscribeInfo(subscribeEmailDto);
        return ok();
    }
}
