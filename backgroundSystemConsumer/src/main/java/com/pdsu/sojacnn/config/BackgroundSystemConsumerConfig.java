package com.pdsu.sojacnn.config;

import com.pdsu.sojacnn.filter.AuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

import javax.servlet.Filter;

/**
 * @author 半梦
 * @create 2021-05-11 20:42
 */
@Configuration
public class BackgroundSystemConsumerConfig {

    @Bean
    public ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("网站-新闻网")
                .description("本文档描述了后台管理微服务接口定义")
                .version("1.0.0")
                .contact(new Contact("半梦Oo", "https://github.com/pyb1430501241", "1430501241@qq.com"))
                .build();
    }

    @Bean
    public FilterRegistrationBean authenticationFilterBean() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new AuthenticationFilter());
        filterRegistrationBean.addUrlPatterns("/*");//拦截所有请求
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);//优先级为最高
        return filterRegistrationBean;
    }

}
