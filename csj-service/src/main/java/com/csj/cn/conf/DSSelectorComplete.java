package com.csj.cn.conf;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description TODO
 * @Author chensijia
 * @Date 2020/4/1618:59
 */
@Component
@Aspect
public class DSSelectorComplete {
    @Before("com.csj.cn.conf.DsPointcut.selectPointCut()")
    public void changeDS(JoinPoint joinPoint) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Method realMethod = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(),method.getParameterTypes());
        DSSelector selector = realMethod.getAnnotation(DSSelector.class);
        if (selector == null) return;

        MultipleDataSourceHelper.set(selector.value());
    }
}
