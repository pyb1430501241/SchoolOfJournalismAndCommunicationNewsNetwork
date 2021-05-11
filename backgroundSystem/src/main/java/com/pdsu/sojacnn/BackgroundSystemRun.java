package com.pdsu.sojacnn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author 半梦
 * @create 2021-05-11 20:38
 */
@SpringCloudApplication
@MapperScan("com.pdsu.sojacnn.mapper")
public class BackgroundSystemRun {

    public static void main(String[] args) {
        SpringApplication.run(BackgroundSystemRun.class, args);
    }

}
