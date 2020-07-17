package com.csj.cn.utils;

//import com.ftop3.api.identity.client.WeixinPay;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/*** Created by Alex.Sun on 2016/8/30.*/
public class BeanUtils extends org.springframework.beans.BeanUtils {
    /**
     * 对象复制，字段排除的方式     *     * @param source     * @param target     * @param properties     * @return     * @throws BeansException
     */
    public static Object copyPropertiesExclude(Object source, Object target, String... properties) throws BeansException {
        copyProperties(source, target, properties);
        return target;
    }

    public static Object copyPropertiesExclude(Object source, Object target, Collection<String> properties) throws BeansException {
        return copyPropertiesExclude(source, target, CollectionUtils.isEmpty(properties) ? ArrayUtils.EMPTY_STRING_ARRAY : properties.toArray(new String[0]));
    }

    /**
     * 对象复制，字段包含的方式     *     * @param source     * @param target     * @param properties     * @return     * @throws BeansException
     */
    public static Object copyPropertiesInclude(Object source, Object target, String... properties) throws BeansException {
        if (properties == null) properties = ArrayUtils.EMPTY_STRING_ARRAY;
        return copyPropertiesInclude(source, target, Arrays.asList(properties));
    }

    public static Object copyPropertiesInclude(Object source, Object target, Collection<String> includeProperties) throws BeansException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        if (includeProperties == null) includeProperties = Collections.emptySet();
        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && includeProperties.contains(targetPd.getName())) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }
                            writeMethod.invoke(target, value);
                        } catch (Throwable ex) {
                            throw new FatalBeanException("Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }
        return target;
    }

    /**
     * 获取指定class的所有属性名     *     * @param claz     * @return
     */
    public static String[] getPropertyNames(Class claz) {
        return getPropertyNames(claz, true);
    }

    public static String[] getPropertyNames(Class claz, boolean writable) {
        return Arrays.stream(getPropertyDescriptors(claz)).filter(p -> writable && p.getWriteMethod() != null).map(p -> p.getName()).collect(Collectors.toList()).toArray(new String[0]);
    }

    public static void main(String[] args) {
//        System.out.println(WeixinPay.class.getSimpleName() + ":" + StringUtils.join(BeanUtils.getPropertyNames(WeixinPay.class), ","));
    }
}