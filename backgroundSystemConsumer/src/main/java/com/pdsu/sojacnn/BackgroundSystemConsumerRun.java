package com.pdsu.sojacnn;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 半梦
 * @create 2021-05-11 20:40
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.pdsu.sojacnn.service"})
public class BackgroundSystemConsumerRun {

    public static void main(String[] args) {
        SpringApplication.run(BackgroundSystemConsumerRun.class, args);
    }

}
