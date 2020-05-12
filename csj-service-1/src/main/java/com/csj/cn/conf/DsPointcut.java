package com.csj.cn.conf;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @Description TODO
 * @Author chensijia
 * @Date 2020/4/1618:57
 */
public class DsPointcut {
    @Pointcut("execution(public * com.csj.cn.service.*.*(..))")
    public void selectPointCut(){

    }
}
