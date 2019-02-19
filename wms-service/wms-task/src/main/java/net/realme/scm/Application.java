package net.realme.scm;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.scm.task
 *
 * @author 91000044
 * @date 2018/9/20 0:46
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@ComponentScan(basePackages = {"net.realme"})
@ImportResource({"classpath:dubbo-spring.xml"})
@EnableScheduling
public class Application {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args)  {
        SpringApplication.run(Application.class, args);
    }
}

