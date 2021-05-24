package com.pdsu.sojacnn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 半梦
 * @create 2021-05-11 20:38
 */
@SpringCloudApplication
@MapperScan("com.pdsu.sojacnn.mapper")
@EnableTransactionManagement
public class BackgroundSystemRun {

    public static void main(String[] args) {
        SpringApplication.run(BackgroundSystemRun.class, args);
    }

}
