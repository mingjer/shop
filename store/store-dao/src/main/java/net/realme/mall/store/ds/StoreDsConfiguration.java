package net.realme.mall.store.ds;

//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
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
 * package: net.realme.mall.store.ds
 *
 * @author 91000044
 * @date 2018/7/24 18:58
 */
//@Configuration
//@MapperScan(basePackages = {"net.realme.mall.store.**.dao"}, sqlSessionFactoryRef = "storeSqlSessionFactory")
//public class StoreDsConfiguration {
//
//    @ConfigurationProperties("spring.datasource.druid")
//    @Bean
//    @Primary
//    DataSource storeDataSource(){
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "storeTransactionManager")
//    @Primary
//    public DataSourceTransactionManager masterTransactionManager() {
//        return new DataSourceTransactionManager(storeDataSource());
//    }
//
//    @Bean(name = "storeSqlSessionFactory")
//    @Primary
//    public SqlSessionFactory omsSqlSessionFactory(@Qualifier("storeDataSource") DataSource storeDataSource)
//            throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(storeDataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources("classpath:net/realme/mall/store/**/dao/*.xml"));
//        return sessionFactory.getObject();
//    }
//
//}
