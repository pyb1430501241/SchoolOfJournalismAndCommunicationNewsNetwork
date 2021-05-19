package com.pdsu.sojacnn;

import com.pdsu.sojacnn.controller.ExceptionController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

/**
 * @author 半梦
 * @create 2021-05-07 20:14
 */
@SpringCloudApplication
@ComponentScan(excludeFilters = {@Filter(type= FilterType.ASSIGNABLE_TYPE, value = {ExceptionController.class})})
@EnableZuulProxy
public class GateWayRun {

    public static void main(String[] args) {
        SpringApplication.run(GateWayRun.class, args);
    }

}
