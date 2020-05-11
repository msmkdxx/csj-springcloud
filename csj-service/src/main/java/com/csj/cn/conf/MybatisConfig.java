package com.csj.cn.conf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "com.csj.cn.mapper")
public class MybatisConfig {
}
