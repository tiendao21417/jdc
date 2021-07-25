package com.jdc.onlineshopping.config;

import com.jdc.onlineshopping.aop.logging.LoggerProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author tiendao on 24/07/2021
 */
@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public DataSource getDataSource() {
        LoggerProvider.APP.info("MYSQL CONNECT");
        LoggerProvider.APP.info("url: " + url);
        LoggerProvider.APP.info("driverClassName: " + driverClassName);
        LoggerProvider.APP.info("username: " + username);
        LoggerProvider.APP.info("password: " + password);
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);
        return dataSourceBuilder.build();
    }
}
