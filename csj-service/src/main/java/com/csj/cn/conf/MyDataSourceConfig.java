package com.csj.cn.conf;

import com.google.common.collect.Maps;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @Description TODO
 * @Author chensijia
 * @Date 2020/4/1617:02
 */
@Configuration
public class MyDataSourceConfig {
    @Resource(name = "dynamicDataSource")
    private DataSource dataSource;
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Primary
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public DataSource dynamicDataSource() {
        Map<Object, Object> dataSourceMap = Maps.newHashMap();
        dataSourceMap.put(MultipleDataSourceHelper.MASTER, masterDataSource());
        dataSourceMap.put(MultipleDataSourceHelper.SLAVE, slaveDataSource());
        DynamicDataSource dds = new DynamicDataSource();
        dds.setTargetDataSources(dataSourceMap);
        dds.setDefaultTargetDataSource(masterDataSource());
        return dds;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        return sqlSessionFactoryBean.getObject();
    }
}
