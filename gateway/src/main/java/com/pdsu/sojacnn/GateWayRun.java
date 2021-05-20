package com.pdsu.sojacnn;

import com.pdsu.sojacnn.service.BackgroundService;
import com.pdsu.sojacnn.service.NewsFeatureService;
import com.pdsu.sojacnn.service.impl.BackgroundServiceFallbackFactory;
import com.pdsu.sojacnn.service.impl.NewsFeatureFallbackFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

/**
 * @author 半梦
 * @create 2021-05-07 20:14
 */
@ComponentScan(excludeFilters = {
        @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
                BackgroundService.class, NewsFeatureService.class,
                BackgroundServiceFallbackFactory.BackgroundServiceFallBack.class,
                BackgroundServiceFallbackFactory.class, NewsFeatureFallbackFactory.class,
                NewsFeatureFallbackFactory.NewsFeatureServiceFallBack.class,
        })
})
@SpringCloudApplication
@EnableZuulProxy
@MapperScan("com.pdsu.sojacnn.mapper")
public class GateWayRun {
    public static void main(String[] args) {
        SpringApplication.run(GateWayRun.class, args);
    }

}
