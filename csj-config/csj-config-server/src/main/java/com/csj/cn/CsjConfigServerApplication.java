package com.csj.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CsjConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsjConfigServerApplication.class, args);
    }

}
