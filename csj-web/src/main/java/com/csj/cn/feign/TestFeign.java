package com.csj.cn.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description TODO
 * @Author chensijia
 * @Date 2020/5/1112:04
 */
@FeignClient(name = "csj-service",fallback = TestFeignHystrix.class)
public interface TestFeign {

    @GetMapping(value = "/test/getNum")
    int getNum(@RequestParam(value = "num") int num);

}
