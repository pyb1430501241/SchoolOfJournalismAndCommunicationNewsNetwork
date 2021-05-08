package com.pdsu.sojacnn;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 半梦
 * @create 2021-05-08 15:31
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.pdsu.sojacnn.service"})
public class NewsFeatureConsumerRun {

    public static void main(String[] args) {
        SpringApplication.run(NewsFeatureConsumerRun.class, args);
    }

}
