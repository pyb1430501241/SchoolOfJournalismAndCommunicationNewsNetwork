package com.pdsu.sojacnn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

/**
 * @author 半梦
 * @create 2021-05-08 18:31
 */
@Configuration
public class NewsFeatureConsumerConfig {

    @Bean
    public ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("网站-新闻网")
                .description("本文档描述了新闻消费者微服务接口定义")
                .version("1.0.0")
                .contact(new Contact("半梦Oo", "https://github.com/pyb1430501241", "1430501241@qq.com"))
                .build();
    }


}
