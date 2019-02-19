package net.realme.framework.rest.client;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.framework.rest.client
 *
 * @author 91000044
 * @date 2018/8/16 16:05
 */
public class RestTemplateImpl implements RestHttpClient {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private RestTemplate restTemplate;

    public RestTemplateImpl(RestTemplate restTemplate) {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        for (int i = 0; i < restTemplate.getMessageConverters().size(); i++) {
            if (restTemplate.getMessageConverters().get(i) instanceof StringHttpMessageConverter) {
                restTemplate.getMessageConverters().remove(i);
                restTemplate.getMessageConverters().add(i, stringHttpMessageConverter);
            }
            if (restTemplate.getMessageConverters().get(i) instanceof MappingJackson2HttpMessageConverter) {
                restTemplate.getMessageConverters().remove(i);
                restTemplate.getMessageConverters().add(i, mappingJackson2HttpMessageConverter);
            }
        }
        this.restTemplate = restTemplate;
    }

    @Override
    public String getForString(String url) {
        try {
            return getForString(new URI(url));
        } catch (URISyntaxException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String getForString(URI url) {
        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public JSONObject getForJson(URI url) {
        return restTemplate.getForObject(url, JSONObject.class);
    }

    @Override
    public String postForString(String url, Object entity) {
        try {
            return postForString(new URI(url), entity);
        } catch (URISyntaxException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String postForString(URI url, Object entity) {
        return restTemplate.postForObject(url, entity, String.class);
    }

    @Override
    public <T> T postForJson(String url, Object entity, Class<T> responseType) {
        return restTemplate.postForObject(url, entity, responseType);
    }

    @Override
    public <T> T post(String url, Map<String, String> params, Class<T> responseType, HttpHeaders headers) {
        HttpEntity<Map<String, String>> request = new HttpEntity<>(params, headers);
        return restTemplate.postForObject(url, request, responseType);
    }

    @Override
    public <T> T postAsForm(String url, Map<String, String> params, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> convertedParams = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            convertedParams.add(entry.getKey(), entry.getValue());
        }
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(convertedParams, headers);
        return restTemplate.postForObject( url, request , responseType);
    }

    @Override
    public <T> T postAsJson(String url, Map<String, String> params, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return post(url, params, responseType, headers);
    }
}
