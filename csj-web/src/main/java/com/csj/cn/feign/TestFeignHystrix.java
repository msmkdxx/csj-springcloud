package com.csj.cn.feign;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description TODO
 * @Author chensijia
 * @Date 2020/5/128:57
 */
@Component
public class TestFeignHystrix implements TestFeign {
    @Override
    public int getNum(@RequestParam(value = "num") int num) {
        return 0;
    }
}
