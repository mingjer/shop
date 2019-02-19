package net.realme.framework.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms.config
 *
 * @author 91000044
 * @date 2018/8/2 11:30
 */
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        logger.error("Exception message - {}",  throwable.getMessage());
        logger.error("Method name - {}", method.getName());
        for (Object param : objects) {
            logger.error("Parameter value - {}", param);
        }
    }
}
