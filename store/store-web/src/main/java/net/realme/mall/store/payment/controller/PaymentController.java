package net.realme.mall.store.payment.controller;

import net.realme.framework.sso.ColorOSUser;
import net.realme.framework.util.constant.CommonStatus;
import net.realme.framework.util.dto.Result;
import net.realme.framework.util.dto.ResultT;
import net.realme.framework.util.text.UrlUtil;
import net.realme.framework.web.controller.LocalizeController;
import net.realme.id.domain.IdBizCode;
import net.realme.id.facade.IdGenerator;
import net.realme.mall.order.consts.OrderConsts;
import net.realme.mall.order.domain.OrderMainDto;
import net.realme.mall.order.facade.OrderService;
import net.realme.mall.payment.beantool.PaymentTxnConverter;
import net.realme.mall.payment.constant.PaymentConstant;
import net.realme.mall.payment.domain.OrderPaymentDto;
import net.realme.mall.payment.domain.PaymentPayload;
import net.realme.mall.payment.domain.PaymentRequestConstant;
import net.realme.mall.payment.domain.PaymentResponse;
import net.realme.mall.payment.facade.PaymentIntegrationStrategy;
import net.realme.mall.payment.facade.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.store.payment.controller
 *
 * @author 91000044
 * @date 2018/9/1 23:53
 */
@Validated
@RestController
public class PaymentController extends LocalizeController {

    @Value("${shopping.site.url.prefix:}")
    private String buyUrlPrefix;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentTxnConverter paymentTxnConverter;

    @Autowired
    private BeanFactory beanFactory;

    @RequestMapping(PaymentRequestConstant.PAYMENT_REDIRECT)
    public RedirectView redirect(@PathVariable String siteCode,
                                 @Min(1) Long orderNo) {
        UriComponentsBuilder resultUrl = UriComponentsBuilder.fromUriString(UrlUtil.join(buyUrlPrefix, siteCode, PaymentRequestConstant.PAYMENT_RESULT_STATIC));
        resultUrl.queryParam("orderNo", orderNo);
        byte bizType = PaymentConstant.BIZ_TYPE_REGULAR;
        ResultT<OrderMainDto> ret = orderService.getOrderInfoByOrderNo(orderNo);
        OrderMainDto orderMainDto = null;
        if (ret.isSuccess()) {
            orderMainDto = ret.getData();
        }
        if (orderMainDto == null || OrderConsts.OrderStatus.UNPAID != orderMainDto.getOrderStatus()) {
            resultUrl.queryParam("msg", "Your order have been expired. ");
            return new RedirectView(resultUrl.build().toString());
        }
        if (orderMainDto.getUserId() != ColorOSUser.getCurrentId()) {
            resultUrl.queryParam("msg", "The order does not belongs to you. ");
            return new RedirectView(resultUrl.build().toString());
        }
        //todo 需要指定一个阈值？
        if (orderMainDto.getOrderTotalAmount() == null || orderMainDto.getOrderTotalAmount().compareTo(BigDecimal.ZERO) == 0) {
            resultUrl.queryParam("msg", "invalid order.");
            return new RedirectView(resultUrl.build().toString());
        }
        String payChannel = orderMainDto.getPayChannel();
        PaymentIntegrationStrategy paymentIntegrationStrategy;
        try {
            paymentIntegrationStrategy = beanFactory.getBean(payChannel, PaymentIntegrationStrategy.class);
        } catch (Exception e) {
            resultUrl.queryParam("msg", "Invalid pay channel.");
            return new RedirectView(resultUrl.build().toString());
        }
        //还没用过的且未过期的（暂15min）流水号可以继续用来发起支付，没有则新建一条
        ResultT<OrderPaymentDto> pendingResult = paymentService.getPendingByBizNo(orderNo);
        OrderPaymentDto pendingPayment = pendingResult.getData();
        OrderPaymentDto orderPaymentDto;
        if (pendingPayment == null || System.currentTimeMillis() - pendingPayment.getCreatedAt() > 900000) {
            orderPaymentDto = new OrderPaymentDto();
            orderPaymentDto.setVersion(1);
            ResultT<Long> idGeneratorResult = idGenerator.get(IdBizCode.PAYMENT, 1);
            if (!idGeneratorResult.isSuccess()) {
                logger.error("generate id for payment failed.");
                resultUrl.queryParam("msg", "server busy now, please try later.");
                return new RedirectView(resultUrl.build().toString());
            }
            long paymentNo = idGeneratorResult.getData();
            orderPaymentDto.setPaymentNo(paymentNo);
            orderPaymentDto.setBizNo(orderNo);
            orderPaymentDto.setSsoid(orderMainDto.getUserId());
            orderPaymentDto.setOrderNo(orderNo);
            orderPaymentDto.setPayChannel(payChannel);
            orderPaymentDto.setTxnAmount(orderMainDto.getOrderTotalAmount());
            orderPaymentDto.setBizType(bizType);
            orderPaymentDto.setStatus(PaymentConstant.STATUS_PENDING);
            orderPaymentDto.setCreatedAt(System.currentTimeMillis());
            ResultT<Long> addPaymentRet  = paymentService.addPayment(orderPaymentDto);
            if (!addPaymentRet.isSuccess()) {
                logger.error("add payment record for order {} failed.", orderNo);
                resultUrl.queryParam("msg", "server busy now, please try later.");
                return new RedirectView(resultUrl.build().toString());
            }
        } else {
            orderPaymentDto = pendingResult.getData();
        }

        PaymentPayload paymentPayload = new PaymentPayload();
        paymentPayload.setSiteCode(siteCode);
        paymentPayload.setPaymentNo(orderPaymentDto.getPaymentNo());
        paymentPayload.setOrderNo(orderNo);
        paymentPayload.setBizType(bizType);
        paymentPayload.setBizNo(orderNo);
        paymentPayload.setAmount(orderMainDto.getOrderTotalAmount());
        paymentPayload.setPayChannel(payChannel);
        String redirectUrl = paymentIntegrationStrategy.makeRedirectPayment(paymentPayload);
        return new RedirectView(redirectUrl, true, true, false);
    }

    @ResponseBody
    @RequestMapping(PaymentRequestConstant.PAYMENT_CALLBACK)
    public RedirectView handleSyncCallback(@PathVariable String siteCode,
                                           @PathVariable String payChannel,
                                           HttpServletRequest request)  {
        UriComponentsBuilder resultUrl = UriComponentsBuilder.fromUriString(UrlUtil.join(buyUrlPrefix, siteCode, PaymentRequestConstant.PAYMENT_RESULT_STATIC));
        PaymentIntegrationStrategy paymentIntegrationStrategy;
        try {
            paymentIntegrationStrategy = beanFactory.getBean(payChannel, PaymentIntegrationStrategy.class);
        } catch (Exception e) {
            logger.error("invalid pay channel: {}", payChannel);
            resultUrl.queryParam("msg", "Invalid pay channel.");
            return new RedirectView(resultUrl.build().toString());
        }
        PaymentResponse paymentResponse = paymentIntegrationStrategy.handleResponse(request);
        if (paymentResponse == null) {
            resultUrl.queryParam("msg",  "Internal Error.");
            return new RedirectView(resultUrl.build().toString());
        }
        paymentService.addTxnLog(paymentTxnConverter.toPaymentTxnLogDto(paymentResponse));
        if (CommonStatus.YES == paymentResponse.getStatus()) {
            resultUrl.queryParam("orderNo",  paymentResponse.getOrderNo());
            resultUrl.queryParam("msg",  "You have finished your payment successfully and we will deliver the product to you as soon as possible");
        } else {
            logger.error("{} failed to finish payment. ", paymentResponse.getOrderNo());
            resultUrl.queryParam("orderNo", paymentResponse.getOrderNo());
            resultUrl.queryParam("msg", paymentResponse.getErrMsg());
        }
        return new RedirectView(resultUrl.build().toString());
    }

    @RequestMapping("/payment/{payChannel}/notify")
    public Result handleAsyncNotify(@PathVariable String payChannel,
                                    HttpServletRequest request) {
        PaymentIntegrationStrategy paymentIntegrationStrategy;
        try {
            paymentIntegrationStrategy = beanFactory.getBean(payChannel, PaymentIntegrationStrategy.class);
        } catch (Exception e) {
            logger.error("invalid payment code: {}", payChannel);
            return err("invalid payment");
        }
        PaymentResponse paymentResponse = paymentIntegrationStrategy.handleResponse(request);
        if (paymentResponse == null) {
            return err("fail to handle payment response");
        }
        paymentService.addTxnLog(paymentTxnConverter.toPaymentTxnLogDto(paymentResponse));
        return ok();
    }

}
