package com.dity.springboot.config;

import com.dity.springboot.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author:yuhang
 * @Date:2018/3/31
 * 主配置类
 */
@Configuration
public class WebConfig {
    private static Map<String,String> map=new LinkedHashMap<>();
    static {
        map.put("/","index.html");
        map.put("/index","index.html");
    }

    /**
     * 配置mvc
     * @return
     */
    @Bean
    public WebMvcConfigurerAdapter mvcConfigurerAdapter(){
        return new WebMvcConfigurerAdapter(){

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
              //  registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/","/index.html","/user/login","/login.html");
            }
        };
    }


    /**
     * 配置国际化
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
