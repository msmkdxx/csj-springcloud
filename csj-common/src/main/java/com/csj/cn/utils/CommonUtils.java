package com.csj.cn.utils;

import java.util.UUID;

/**
 * @Description TODO 随机数生成
 */
public class CommonUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }
}
