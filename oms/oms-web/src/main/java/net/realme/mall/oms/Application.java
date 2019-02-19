package net.realme.mall.oms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.oms
 *
 * @author 91000044
 * @date 2018/7/24 20:31
 */
@EnableTransactionManagement
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@ImportResource({"classpath:dubbo-spring.xml"})
@ComponentScan(basePackages = {"net.realme"})
public class Application {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args)  {
        SpringApplication.run(Application.class, args);
    }
}
