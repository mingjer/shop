package net.realme.mall.product.dao.ds;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * Copyright Realme Platform Development, All rights reserved.
 * <p>
 * <p>
 * package: net.realme.mall.product.dao.config
 *
 * @author 91000044
 * @date 2018/7/24 18:58
 */
@Configuration
@MapperScan(basePackages = "net.realme.mall.product.dao", sqlSessionFactoryRef = "productSqlSessionFactory")
public class ProductDsConfiguration {

    @ConfigurationProperties("spring.datasource.druid")
    @Bean
    @Primary
    DataSource productDataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "productTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(productDataSource());
    }

    @Bean(name = "productSqlSessionFactory")
    @Primary
    public SqlSessionFactory productSqlSessionFactory(@Qualifier("productDataSource") DataSource productDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(productDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:net/realme/mall/product/dao/*.xml"));
        return sessionFactory.getObject();
    }

}
