package net.realme.mall.store.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.store.msg
 *
 * @author 91000044
 * @date 2018/9/13 13:03
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ImportResource({"classpath:dubbo-spring.xml"})
@ComponentScan(basePackages = {"net.realme"})
public class Application implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
