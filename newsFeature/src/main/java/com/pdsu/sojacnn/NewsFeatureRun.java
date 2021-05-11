package com.pdsu.sojacnn;

import com.pdsu.sojacnn.service.NewsFeatureService;
import com.pdsu.sojacnn.service.impl.NewsFeatureFallbackFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author 半梦
 * @create 2021-05-07 16:52
 */
@SpringCloudApplication
@MapperScan("com.pdsu.sojacnn.mapper")
public class NewsFeatureRun {

    public static void main(String[] args) {
        SpringApplication.run(NewsFeatureRun.class, args);
    }

}
