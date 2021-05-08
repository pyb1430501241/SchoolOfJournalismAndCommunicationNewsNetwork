package com.pdsu.sojacnn.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

import javax.sql.DataSource;

/**
 * @author 半梦
 * @create 2021-05-07 17:49
 */
@Configuration
public class NewsFeatureConfig {

    @Bean
    public DataSource hikariDataSource(@Qualifier("hikariConfig") HikariConfig config) {
        return new HikariDataSource(config);
    }

    @ConfigurationProperties("spring.datasource.hikari")
    @Bean
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("网站-新闻网")
                .description("本文档描述了新闻生产者微服务接口定义")
                .version("1.0.0")
                .contact(new Contact("半梦Oo", "https://github.com/pyb1430501241", "1430501241@qq.com"))
                .build();
    }


}
