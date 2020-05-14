package com.csj.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
public class CsjAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsjAdminApplication.class, args);
    }

}
