package com.dity.redis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author:yuhang
 * @Date:2018/4/25
 */
//@Configuration
public class CorsConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/*")
                .allowedMethods("*")//允许所有的请求方式
                .allowedHeaders("*")//允许所有的请求头
                .allowedOrigins("*");//允许所有的请求域名
    }
}
