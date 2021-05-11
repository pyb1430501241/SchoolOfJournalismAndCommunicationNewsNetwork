package com.pdsu.sojacnn.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author 半梦
 * @create 2021-05-11 20:59
 */
@Configuration
public class BackgroundSystemConfig {

    @Bean
    public DataSource hikariDataSource(@Qualifier("hikariConfig") HikariConfig config) {
        return new HikariDataSource(config);
    }

    @ConfigurationProperties("spring.datasource.hikari")
    @Bean
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

}
