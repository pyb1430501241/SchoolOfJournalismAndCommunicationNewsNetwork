package com.pdsu.sojacnn.config;

import com.pdsu.sojacnn.aspect.LoggingAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 半梦
 * @create 2021-05-29 15:21
 */
@Configuration
@ConditionalOnClass(name = "com.mysql.cj.jdbc.Driver")
public class LoggingConfig {

    @Bean
    public LoggingAspect loggingAspect() {
        return new LoggingAspect();
    }

}
