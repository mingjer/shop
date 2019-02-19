package net.realme.mall.oms.config.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.session.web.http.HttpSessionIdResolver;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.config
 *
 * @author 91000044
 * @date 2018/8/9 11:41
 */
@Profile("dev")
@Configuration
@EnableRedisHttpSession
public class DevHttpSessionConfig extends AbstractHttpSessionApplicationInitializer {

    @Bean
    public ConfigureRedisAction configureRedisAction() {
        return ConfigureRedisAction.NO_OP;
    }

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return new CustomHttpSessionIdResolver();
    }
}
