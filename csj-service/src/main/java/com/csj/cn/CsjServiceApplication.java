package com.csj.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CsjServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsjServiceApplication.class, args);
    }

}
