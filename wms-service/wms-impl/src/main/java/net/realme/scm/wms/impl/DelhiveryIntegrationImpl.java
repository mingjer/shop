package net.realme.scm.wms.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.realme.framework.rest.client.RestHttpClient;
import net.realme.framework.util.dto.ResultT;
import net.realme.mall.order.facade.OrderService;
import net.realme.scm.wms.domain.NotificationResponse;
import net.realme.scm.wms.domain.PushOrderPayload;
import net.realme.scm.wms.domain.PushProductPayload;
import net.realme.scm.wms.domain.delhivery.DelhiveryProduct;
import net.realme.scm.wms.domain.delhivery.DelhiveryResponse;
import net.realme.scm.wms.facade.WmsIntegration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.scm.wms.impl
 *
 * @author 91000044
 * @date 2018/9/19 14:57
 */
@Component("delhivery")
public class DelhiveryIntegrationImpl implements WmsIntegration {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String CREATE_PRODUCT_URI = "/product/create/";
    private static final String CREATE_ORDER_URI = "/order/create/";

    @Value("${wms.delhivery.host}")
    private String apiHost;

    @Value("${wms.delhivery.account.2c.accessKey}")
    private String accessKey;

    @Value("${wms.delhivery.account.2c.clientKey}")
    private String clientKey;

    @Value("${wms.delhivery.account.2c.supplierKey}")
    private String supplierKey;

    @Value("${wms.delhivery.account.2c.token}")
    private String token;

    @Autowired
    private RestHttpClient restHttpClient;

    @Autowired
    private OrderService orderService;

    @Override
    public ResultT<Boolean> pushProduct(PushProductPayload payload) {
        DelhiveryProduct delhiveryProduct = (DelhiveryProduct) payload;
        delhiveryProduct.setClientKey(clientKey);
        delhiveryProduct.setSupplierKey(supplierKey);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Token " + token);
        headers.add("Version", "V2 ");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String body = objectMapper.writeValueAsString(payload);
            logger.info("product creation request body: {}", body);
            HttpEntity<String> entity = new HttpEntity<>(body, headers);
            DelhiveryResponse response = restHttpClient.postForJson(apiHost + CREATE_PRODUCT_URI, entity, DelhiveryResponse.class);
            logger.info("response from delhivery: {}", response);
            if (response == null || !response.getAcknowledged()) {
                return ResultT.fail(response == null ? "" : response.getMessage());
            }
            return ResultT.success();
        } catch (Exception e) {
            logger.error("request for delhivery failed. {}", e.getMessage(), e);
        }
        return ResultT.fail();
    }
    public ResultT<Boolean> pushProductList(List<? extends PushProductPayload> payload) {
       ArrayList<PushProductPayload> delhiveryProduct = (ArrayList<PushProductPayload>) payload;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Token " + token);
        headers.add("Version", "V2 ");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String body = objectMapper.writeValueAsString(payload);
            logger.info("product creation request body: {}", body);
            HttpEntity<String> entity = new HttpEntity<>(body, headers);
            DelhiveryResponse response = restHttpClient.postForJson(apiHost + CREATE_PRODUCT_URI, entity, DelhiveryResponse.class);
            logger.info("response from delhivery: {}", response);
            if (response == null || !response.getAcknowledged()) {
                return ResultT.fail(response == null ? "" : response.getMessage());
            }
            return ResultT.success();
        } catch (Exception e) {
            logger.error("request for delhivery failed. {}", e.getMessage(), e);
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<Boolean> pushOrder(List<? extends PushOrderPayload> payload) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Token " + token);
        headers.add("Version", "V2 ");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String body = objectMapper.writeValueAsString(payload);
            logger.info("order creation request body: {}", body);
            HttpEntity<String> entity = new HttpEntity<>(body, headers);
            DelhiveryResponse response = restHttpClient.postForJson(apiHost + CREATE_ORDER_URI, entity, DelhiveryResponse.class);
            logger.info("response from delhivery: {}", response);
            if (response == null || !response.getAcknowledged()) {
                return ResultT.fail(response == null ? "" : response.getMessage());
            }
            return ResultT.success();
        } catch (Exception e) {
            logger.error("request for delhivery failed. {}", e.getMessage(), e);
        }
        return ResultT.fail();
    }

    @Override
    public ResultT<Boolean> notifyOrder(NotificationResponse notificationResponse) {
        return null;
    }

    @Override
    public ResultT<Boolean> notifyInBound(NotificationResponse notificationResponse) {
        return null;
    }

    @Override
    public ResultT<Boolean> notifyInventory(NotificationResponse notificationResponse) {
        return null;
    }
}
