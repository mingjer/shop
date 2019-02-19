package net.realme.framework.rest.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.framework.rest.client
 *
 * @author 91000044
 * @date 2018/8/17 16:38
 */
@Configuration
@ConditionalOnClass(RestHttpClient.class)
@EnableConfigurationProperties(RestHttpClientProperties.class)
public class RestHttpClientAutoConfigure {

    @Autowired
    private RestHttpClientProperties properties;


    @Bean
    public ClientHttpRequestFactory customHttpRequestFactory()
    {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(properties.getRequestTimeout());
        httpComponentsClientHttpRequestFactory.setConnectTimeout(properties.getConnectTimeout());
        httpComponentsClientHttpRequestFactory.setReadTimeout(properties.getReadTimeout());
        return httpComponentsClientHttpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate(customHttpRequestFactory());
    }

    @Bean
    @ConditionalOnMissingBean
    public RestHttpClient restHttpClient() {
        return new RestTemplateImpl(restTemplate());
    }
}
