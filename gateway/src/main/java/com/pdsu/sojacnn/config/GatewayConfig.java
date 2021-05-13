package com.pdsu.sojacnn.config;

import com.pdsu.sojacnn.bean.CrossConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

/**
 * @author 半梦
 * @create 2021-05-07 20:25
 */
@Log4j2
//如果有多个配置文件，以这个为准
@Primary
@Configuration
public class GatewayConfig {

    /**
     * 跨域
     */
    @Bean
    public CorsConfiguration buildConfig(CrossConfig crossConfig) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(format(crossConfig.getAllowIpOrigin()));
        log.info("系统初始化...允许以下 IP 进行访问: " + corsConfiguration.getAllowedOrigins());
        corsConfiguration.setAllowedHeaders(format(crossConfig.getAllowHeaderOrigin()));
        log.info("系统初始化...允许添加以下请求头: " + corsConfiguration.getAllowedHeaders());
        corsConfiguration.setAllowedMethods(format(crossConfig.getAllowMethodOrigin()));
        log.info("系统初始化...允许以下请求方式访问: " + corsConfiguration.getAllowedMethods());
        corsConfiguration.setExposedHeaders(format(crossConfig.getExposedHeaderOrigin()));
        log.info("系统初始化...允许以下请求头暴露: " + corsConfiguration.getExposedHeaders());
        corsConfiguration.setAllowCredentials(true);
        log.info("系统初始化...是否允许保持用户认证状态: " + corsConfiguration.getAllowCredentials());
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter(CorsConfiguration configuration) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); //注册
        return new CorsFilter(source);
    }

    private List<String> format(String ... all) {
        return Arrays.asList(all);
    }

    @Bean
    public Docket createRestApi(ApiInfo apiInfo) {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo);
    }

    @Bean
    public ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("网站-新闻网")
                .description("本文档描述了新闻网全体服务")
                .version("1.0.0")
                .contact(new Contact("半梦Oo", "https://github.com/pyb1430501241", "1430501241@qq.com"))
                .build();
    }



}
