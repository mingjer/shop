package net.realme.framework.rest.client;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpHeaders;

import java.net.URI;
import java.util.Map;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.framework.rest.client
 *
 * @author 91000044
 * @date 2018/8/16 16:09
 */
public interface RestHttpClient {

    String getForString(String url);

    String getForString(URI url);

    JSONObject getForJson(URI uri);

    String postForString(String url, Object body);

    String postForString(URI url, Object entity);

    <T> T postForJson(String url, Object entity, Class<T> responseType);

    <T> T post(String url, Map<String, String> params, Class<T> responseType, HttpHeaders headers);
    /**
     * form-urlencoded方式提交，结果为json
     * @param url
     * @param params
     * @param responseType
     * @param <T>
     * @return
     */
    <T> T postAsForm(String url, Map<String, String> params, Class<T> responseType);

    /**
     * 提交及结果都是json
     * @param url
     * @param params
     * @param responseType
     * @param <T>
     * @return
     */
    <T> T postAsJson(String url, Map<String, String> params, Class<T> responseType);
}
