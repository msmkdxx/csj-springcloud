package com.csj.cn.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author chensijia
 * @Date 2020/5/1410:36
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Value("${test.config}")
    private String config;

    @GetMapping(value = "/getConfig")
    public String getConfig(){
        return config;
    }
}
