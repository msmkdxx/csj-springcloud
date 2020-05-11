package com.csj.cn.controller;

import com.csj.cn.feign.TestFeign;
import io.swagger.annotations.Api;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author chensijia
 * @Date 2020/5/1112:08
 */
@RestController
@RequestMapping(value = "/feign")
public class TestFeignController {
    @Autowired
    private TestFeign testFeign;

    @GetMapping(value = "/getNums")
    public int getNums(@RequestParam(value = "num") int num) {
        return testFeign.getNum(num);
    }
}
