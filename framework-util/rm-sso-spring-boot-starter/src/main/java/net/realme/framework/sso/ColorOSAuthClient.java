package net.realme.framework.sso;

import net.realme.framework.rest.client.RestHttpClient;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.util.TreeMap;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.store.config.security
 *
 * @author 91000044
 * @date 2018/8/31 10:16
 */
@Component
public class ColorOSAuthClient {

    @Autowired
    private SsoConfigProperties ssoConfigProperties;

    private final Logger logger = LoggerFactory.getLogger(getClass());


    private static final String TOKEN_AUTH_URI = "/token/auth";
    private static final String TOKEN_DETAIL_URI = "/token/detail-info";
    private static final String SESSION_DETAIL_URI = "/session/detail-info";


    @Autowired
    private RestHttpClient httpClient;

    private String getSignature(TreeMap<String, String> params) {
        StringBuilder keyValueStr = new StringBuilder();
        for (String key : params.keySet()) {
            if (StringUtils.isNotEmpty(params.get(key))) {
                keyValueStr.append(key).append("=").append(params.get(key)).append("&");
            }
        }
        keyValueStr.append("key=").append(ssoConfigProperties.getAppSecret());
        logger.debug("keyValueStr: {}", keyValueStr);
        return DigestUtils.md5Hex(keyValueStr.toString());
    }


    public ColorOSAuthResponse tokenAuth(String token, String imei, String clientIp) {
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appKey", ssoConfigProperties.getAppKey());
        params.put("timestamp", "" + System.currentTimeMillis());
        params.put("token", token);
        params.put("imei", imei);
        params.put("clientIp", clientIp);
        params.put("appPackage", ssoConfigProperties.getAppPackage());
        params.put("sign", getSignature(params));
        try {
            return httpClient.postAsJson(ssoConfigProperties.getHost() + TOKEN_AUTH_URI, params, ColorOSAuthResponse.class);
        } catch (RestClientException e) {
            logger.error("tokenAuth request failed. ", e);
        } catch (Exception e) {
            logger.error("{}", e);
        }
        return null;
    }


    public ColorOSAuthResponse tokenAuthDetail(String token, String imei, String clientIp) {
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appKey", ssoConfigProperties.getAppKey());
        params.put("timestamp", "" + System.currentTimeMillis());
        params.put("token", token);
        params.put("imei", imei);
        params.put("clientIp", clientIp);
        params.put("appPackage", ssoConfigProperties.getAppPackage());
        params.put("sign", getSignature(params));
        try {
            return httpClient.postAsJson(ssoConfigProperties.getHost() + TOKEN_DETAIL_URI, params, ColorOSAuthResponse.class);
        } catch (RestClientException e) {
            logger.error("tokenAuthDetail request failed. ", e);
        } catch (Exception e) {
            logger.error("{}", e);
        }
        return null;
    }


    public ColorOSAuthResponse sessionAuthDetail(String sessionId, String imei, String clientIp) {
        TreeMap<String, String> params = new TreeMap<>();
        params.put("appKey", ssoConfigProperties.getAppKey());
        params.put("timestamp", "" + System.currentTimeMillis());
        params.put("sessionId", sessionId);
        params.put("imei", imei);
        params.put("clientIp", clientIp);
        params.put("appPackage", ssoConfigProperties.getAppPackage());
        params.put("sign", getSignature(params));
        try {
            return httpClient.postAsJson(ssoConfigProperties.getHost() + SESSION_DETAIL_URI, params, ColorOSAuthResponse.class);
        } catch (RestClientException e) {
            logger.error("sessionAuthDetail request failed. ", e);
        } catch (Exception e) {
            logger.error("{}", e);
        }
        return null;
    }
}
