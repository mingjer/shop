package net.realme.framework.rest.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.framework.rest.client
 *
 * @author 91000044
 * @date 2018/8/17 16:39
 */
@ConfigurationProperties(prefix = "rm.rest.client")
public class RestHttpClientProperties {
     private Integer requestTimeout = 5000;
     private Integer connectTimeout = 2000;
     private Integer readTimeout = 5000;

    public Integer getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(Integer requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }
}
