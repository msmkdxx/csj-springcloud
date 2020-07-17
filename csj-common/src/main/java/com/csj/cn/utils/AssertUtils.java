package com.csj.cn.utils;

import org.springframework.util.Assert;


/**
 * 对Assert的包装，去掉deprecated警告
 * <p>
 */
public class AssertUtils {
    public static void notNull(Object object) {
        Assert.notNull(object, "不能为空");
    }


    public static void isTrue(boolean expression) {
        Assert.isTrue(expression, "条件不符");
    }


    public static <T> T notNullEntity(T object) {
        if (object == null) throw new RuntimeException("对象不能为空");
        return object;
    }


    public static void hasText(String text) {
        Assert.hasText(text, "文本为空");
    }
}
