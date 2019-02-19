package net.realme.mall.payment.facade;


import net.realme.mall.payment.domain.PaymentPayload;
import net.realme.mall.payment.domain.PaymentResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.store.facade.payment
 *
 * @author 91000044
 * @date 2018/9/1 23:22
 */
public interface PaymentIntegrationStrategy {

    String makeRedirectPayment(PaymentPayload paymentPayload);

    PaymentResponse handleResponse(HttpServletRequest request);

    PaymentResponse queryTxn(long orderNo);
}
