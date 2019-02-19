package net.realme.mall.store.task.payment;

import net.realme.framework.util.dto.ResultT;
import net.realme.mall.order.consts.OrderConsts;
import net.realme.mall.payment.beantool.PaymentTxnConverter;
import net.realme.mall.payment.constant.PaymentConstant;
import net.realme.mall.payment.domain.OrderPaymentDto;
import net.realme.mall.payment.domain.PaymentResponse;
import net.realme.mall.payment.facade.PaymentIntegrationStrategy;
import net.realme.mall.payment.facade.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.store.task.payment
 *
 * @author 91000044
 * @date 2018/9/8 12:51
 */
@Component
public class BillDeskQueryTask {

    private static final int BATCH_SIZE = 5000;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = PaymentConstant.CHANNEL_BILLDESK)
    private PaymentIntegrationStrategy billdeskIntegration;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentTxnConverter paymentTxnConverter;

    @Scheduled(fixedRate = 300000)
    public void scheduledQuery() {
        //billdesk至少在初始化支付后半小时再去调用查询接口
        long to = System.currentTimeMillis() - 300000;
        long from = to - OrderConsts.ORDER_EXPIRED_TIME * 1000;
        ResultT<Long> countRet = paymentService.countPendingOrderPayments(from, to);
        if(!countRet.isSuccess() || countRet.getData() <= 0) {
            return;
        }
        long total = countRet.getData();
        int pageTotal = (Math.toIntExact(total) + BATCH_SIZE - 1) / BATCH_SIZE;
        for (int p = 0; p < pageTotal; p++) {
            ResultT<List<Long>> result = paymentService.getPendingOrderPayments(from, to, p, BATCH_SIZE);
            List<Long> paymentNos = result.getData();
            for (long paymentNo : paymentNos) {
                PaymentResponse paymentResponse = billdeskIntegration.queryTxn(paymentNo);
                if (paymentResponse != null) {
                    ResultT<Long> updateResult = paymentService.addTxnLog(paymentTxnConverter.toPaymentTxnLogDto(paymentResponse));
                    if (updateResult.isSuccess()) {
                        logger.info("orderNo[{}] update payment success", paymentResponse.getOrderNo());
                    } else {
                        logger.error("orderNo[{}] update payment status failed.", paymentResponse.getOrderNo());
                    }
                }
            }
        }
    }
}
