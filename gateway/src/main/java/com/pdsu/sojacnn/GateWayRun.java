package com.pdsu.sojacnn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 半梦
 * @create 2021-05-07 20:14
 */
@SpringCloudApplication
@EnableZuulProxy
public class GateWayRun {

    public static void main(String[] args) {
        SpringApplication.run(GateWayRun.class, args);
    }

}
