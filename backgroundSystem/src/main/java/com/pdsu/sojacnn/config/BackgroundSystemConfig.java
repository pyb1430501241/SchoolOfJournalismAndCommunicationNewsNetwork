package com.pdsu.sojacnn.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.lang.NonNull;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * @author 半梦
 * @create 2021-05-11 20:59
 */
@Configuration
public class BackgroundSystemConfig implements TransactionManagementConfigurer {

    @Bean
    public DataSource hikariDataSource(@Qualifier("hikariConfig") HikariConfig config) {
        return new HikariDataSource(config);
    }

    @ConfigurationProperties("spring.datasource.hikari")
    @Bean
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @NonNull
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return txManager();
    }

    @Bean
    public PlatformTransactionManager txManager() {
          return new DataSourceTransactionManager(hikariDataSource(hikariConfig()));
    }

}
