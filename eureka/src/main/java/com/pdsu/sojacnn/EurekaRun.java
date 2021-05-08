package com.pdsu.sojacnn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 半梦
 * @create 2021-05-07 18:51
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaRun {

    public static void main(String[] args) {
        SpringApplication.run(EurekaRun.class, args);
    }

}
