/*
package com.csj.cn.conf;

import com.csj.cn.security.JwtAuthenticationFilter;
import com.csj.cn.security.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

*/
/**
 * Spring Security Config
 *//*

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    */
/**
     * 用户认证配置
     *//*

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        */
/**
         * 指定用户认证时，默认从哪里获取认证用户信息
         *//*

        auth.userDetailsService(userDetailsService);
        // 使用自定义身份验证组件
//        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
    }

*/
/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
        http.cors().and().csrf().disable()
                .authorizeRequests()
                // 跨域预检请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // web jars
                .antMatchers("/webjars/**").permitAll()
                // 查看SQL监控（druid）
                .antMatchers("/druid/**").permitAll()
                // 首页和登录页面
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                // swagger
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
                // 验证码
//            .antMatchers("/captcha.jpg**").permitAll()
                // 服务监控
                .antMatchers("/actuator/**").permitAll()
                // 其他所有请求需要身份认证
                .anyRequest().authenticated();
        // 退出登录处理器
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        // token验证过滤器
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }*//*


    */
/**
     * Http安全配置
     *//*

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        */
/**
         * 表单登录：使用默认的表单登录页面和登录端点/login进行登录
         * 退出登录：使用默认的退出登录端点/logout退出登录
         * 记住我：使用默认的“记住我”功能，把记住用户已登录的Token保存在内存里，记住30分钟
         * 权限：除了/toHome和/toUser之外的其它请求都要求用户已登录
         * 注意：Controller中也对URL配置了权限，如果WebSecurityConfig中和Controller中都对某文化URL配置了权限，则取较小的权限
         *//*

        http
                .formLogin()
                .defaultSuccessUrl("/toHome", false)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .rememberMe()
                .tokenValiditySeconds(1800)
                .and()
                .authorizeRequests()
                .antMatchers("/toHome", "/toUser")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

*/
/*    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }*//*


    */
/**
     * 密码加密器
     *//*

    @Bean
    public PasswordEncoder passwordEncoder() {
        */
/**
         * BCryptPasswordEncoder：相同的密码明文每次生成的密文都不同，安全性更高
         *//*

        return new BCryptPasswordEncoder();
    }

}*/
