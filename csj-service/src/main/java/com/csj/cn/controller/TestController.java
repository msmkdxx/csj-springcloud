package com.csj.cn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author chensijia
 * @Date 2020/5/1112:03
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @GetMapping(value = "/getNum")
    public int getNum(@RequestParam(value = "num") int num) {
        return num + 1;
    }
}
