package net.realme.mall.payment.billdesk.impl;

import net.realme.framework.rest.client.RestHttpClient;
import net.realme.framework.util.text.RMTextUtil;
import net.realme.framework.util.text.UrlUtil;
import net.realme.framework.util.time.TimeUtil;
import net.realme.framework.util.time.TimeZoneConstant;
import net.realme.mall.payment.billdesk.dto.BillDeskAuthStatus;
import net.realme.mall.payment.billdesk.dto.BillDeskRefundStatus;
import net.realme.mall.payment.constant.PaymentConstant;
import net.realme.mall.payment.domain.PaymentPayload;
import net.realme.mall.payment.domain.PaymentRequestConstant;
import net.realme.mall.payment.domain.PaymentResponse;
import net.realme.mall.payment.facade.PaymentIntegrationStrategy;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.store.impl.payment
 *
 * @author 91000044
 * @date 2018/9/1 23:31
 */
@Component("billdesk")
public class BillDeskIntegrationImpl implements PaymentIntegrationStrategy {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${payment.in.billdesk.merchant-id}")
    private String merchantId;

    @Value("${payment.in.billdesk.security-id}")
    private String securityId;

    @Value("${payment.in.billdesk.checksum-key}")
    private String checksumKey;

    @Value("${payment.in.billdesk.redirect-url}")
    private String redirectUrl;

    @Value("${payment.in.billdesk.query-url}")
    private String queryUrl;

    @Value("${payment.url.prefix}")
    private String paymentUrlPrefix;

    @Autowired
    private RestHttpClient restHttpClient;

    /**
     * MerchantID|CustomerID|NA|TxnAmount|NA|NA|NA|CurrencyType|NA|TypeField1|SecurityID
     * |NA|NA|TypeField2|OrderNo|AdditionalInfo2|AdditionalInfo3|AdditionalInfo4
     * |AdditionalInfo5|AdditionalInfo6|AdditionalInfo7|RU|Checksum
     *
     * @param paymentPayload
     * @return
     */
    @Override
    public String makeRedirectPayment(PaymentPayload paymentPayload) {
        String msg;
        String returnUrl = UrlUtil.join(paymentUrlPrefix,
                paymentPayload.getSiteCode(),
                PaymentRequestConstant.PAYMENT_CALLBACK.replace("{payChannel}", PaymentConstant.CHANNEL_BILLDESK)
        );

        msg = String.format("%s|%s|NA|%s|NA|NA|NA|INR|NA|R|%s|NA|NA|F|NA|NA|%s|NA|NA|NA|NA|%s",
                merchantId,
                paymentPayload.getPaymentNo(),
                new DecimalFormat("0.00").format(paymentPayload.getAmount()),
                securityId,
                paymentPayload.getOrderNo(),
                returnUrl
                );
        String checkSum = RMTextUtil.hmacHash256(msg, checksumKey, false);
        msg += "|" + checkSum;
        logger.info(msg);
        return redirectUrl + "?msg=" + msg;
    }

    /**
     *
     * MerchantID|CustomerID|TxnReferenceNo|BankReferenceNo|TxnAmount|BankID|BankMerchantID
     * |TxnType|CurrencyName|ItemCode|SecurityType|SecurityID|SecurityPassword|TxnDate
     * |AuthStatus|SettlementType|OrderNo|AdditionalInfo2|AdditionalInfo3|AdditionalInfo4
     * |AdditionalInfo5|AdditionalInfo6|AdditionalInfo7|ErrorStatus|ErrorDescription|CheckSum

     * @param request
     * @return
     */
    @Override
    public PaymentResponse handleResponse(HttpServletRequest request) {
        String msg = request.getParameter("msg");
        logger.info("billdesk response: {}", msg);
        if (StringUtils.isBlank(msg)) {
            return null;
        }
        int pos = msg.lastIndexOf("|");
        if (pos <= 0  || pos == msg.length() - 1) {
            return null;
        }
        String checksum = msg.substring(pos + 1);
        String paramStr = msg.substring(0, pos);
        if (!checksum.equals(RMTextUtil.hmacHash256(paramStr, checksumKey, false))) {
            return null;
        }
        String[] params = paramStr.split("\\|");

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setRawText(msg);
        paymentResponse.setPaymentAccountId(params[0]);
        paymentResponse.setPaymentNo(Long.valueOf(params[1]));
        paymentResponse.setTxnNo(params[2]);
        paymentResponse.setBankTxnNo(params[3]);
        paymentResponse.setTxnAmount(new BigDecimal(params[4]));
        paymentResponse.setBankId(params[5]);
        paymentResponse.setTxnType(params[7]);
        paymentResponse.setCurrencyCode(params[8]);
        paymentResponse.setTxnTime(TimeUtil.localStrToTimestamp(params[13], "dd-MM-yyyy HH:mm:ss", TimeZoneConstant.ZONE_INDIA));
        if(BillDeskAuthStatus.SUCCESS.equals(params[14])) {
            paymentResponse.setStatus(PaymentConstant.STATUS_SUCCESS);
        } else {
            paymentResponse.setStatus(PaymentConstant.STATUS_FAILURE);
        }
        paymentResponse.setPayChannel(PaymentConstant.CHANNEL_BILLDESK);
        paymentResponse.setOrderNo(Long.valueOf(params[18]));
        paymentResponse.setBizNo(paymentResponse.getOrderNo());
        paymentResponse.setErrCode(params[23]);
        paymentResponse.setErrMsg(params[24]);
        return paymentResponse;
    }


    /**
     * RequestType|MerchantID|CustomerID|TxnReferenceNo|BankReferenceNo|TxnAmount|BankID
     * |BankMerchantID|TxnType|CurrencyName|ItemCode|SecurityType|SecurityID|SecurityPassword
     * |TxnDate|AuthStatus|SettlementType|OrderNo|AdditionalInfo2|AdditionalInfo3
     * |AdditionalInfo4|AdditionalInfo5|AdditionalInfo6|AdditionalInfo7|ErrorStatus|ErrorDescription
     * |Filler1|RefundStatus|TotalRefundAmount|LastRefundDate|LastRefundRefNo|QueryStatus|CheckSum
     * @param paymentNo
     * @return
     */
    @Override
    public PaymentResponse queryTxn(long paymentNo) {
        String msg;

        long ts = System.currentTimeMillis();
        msg = String.format("%s|%s|%s|%s",
                "0122",
                merchantId,
                paymentNo,
                TimeUtil.timestampToLocalStr(ts, "yyyyMMddHHmmss", TimeZoneConstant.ZONE_INDIA)
        );
        String reqChecksum = RMTextUtil.hmacHash256(msg, checksumKey, false);
        msg += "|" + reqChecksum;
        logger.info("{} query request: {}", paymentNo, msg);
        HashMap<String, String> body = new HashMap<>();
        body.put("msg", msg);
        String response = restHttpClient.postAsForm(queryUrl, body, String.class);
        if (StringUtils.isBlank(response)) {
            return null;
        }
        logger.info("{} query response: {}", paymentNo,  response);
        int pos = response.lastIndexOf("|");
        if (pos <= 0  || pos == response.length() - 1) {
            return null;
        }
        String respChecksum = response.substring(pos + 1);
        String paramStr = response.substring(0, pos);
        if (!respChecksum.equals(RMTextUtil.hmacHash256(paramStr, checksumKey, false))) {
            return null;
        }
        String[] params = paramStr.split("\\|");
        String queryStatus = params[31];
        if ("N".equals(queryStatus)) {
            return null;
        }
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setRawText(response);
        paymentResponse.setPaymentAccountId(params[1]);
        paymentResponse.setPaymentNo(Long.valueOf(params[2]));
        paymentResponse.setTxnNo(params[3]);
        paymentResponse.setBankTxnNo(params[4]);
        paymentResponse.setTxnAmount(new BigDecimal(params[5]));
        paymentResponse.setBankId(params[6]);
        paymentResponse.setBankMerchantId(params[7]);
        paymentResponse.setCurrencyCode(params[9]);
        paymentResponse.setTxnTime(TimeUtil.localStrToTimestamp(params[14], "dd-MM-yyyy HH:mm:ss", TimeZoneConstant.ZONE_INDIA));
        String authStatus = params[15];
        String refundStatus = params[27];
        //todo: refund等状态未做处理
        int status = (BillDeskAuthStatus.SUCCESS.equals(authStatus) && BillDeskRefundStatus.NONE.equals(refundStatus)) ? 1 : 0;
        paymentResponse.setStatus((byte)status);
        paymentResponse.setPayChannel(PaymentConstant.CHANNEL_BILLDESK);
        paymentResponse.setOrderNo(Long.valueOf(params[19]));
        paymentResponse.setBizNo(paymentResponse.getOrderNo());
        paymentResponse.setErrCode(params[25]);
        paymentResponse.setErrMsg(params[26]);
        return paymentResponse;
    }
}
