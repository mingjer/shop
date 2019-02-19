package net.realme.mall.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
@ImportResource({"classpath:dubbo-spring.xml"})
@ComponentScan(basePackages = {"net.realme"})
@EnableScheduling
public class Application {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args)  {
        SpringApplication.run(Application.class, args);
    }
}
