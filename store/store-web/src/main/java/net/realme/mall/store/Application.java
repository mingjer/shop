package net.realme.mall.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/** 
 * @Author: 91000156
 * @Date: 2018/8/24 11:13
 * @Description:
 */  
//@EnableTransactionManagement
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@ImportResource({"classpath:dubbo-spring.xml"})
@ComponentScan(basePackages = {"net.realme"})
public class Application {
    private Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String[] args)  {
        SpringApplication.run(Application.class, args);
    }
}
