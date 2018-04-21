package com.dity.jpa.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author:yuhang
 * @Date:2018/4/21
 * druid数据源配置类
 * 目的是添加druid监控
 */
@Configuration
public class DruidConfig {

    /**
     * 注册druid的servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServlet(){
        ServletRegistrationBean bean=
                new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //ip白名单
        bean.addInitParameter("allow","127.0.0.1");
        //ip黑名单
        bean.addInitParameter("deny","");
        //控制台登录用户
        bean.addInitParameter("loginUsername","admin");
        //控制台登录密码
        bean.addInitParameter("loginPassword","123456");
        //是否可以重置数据
        bean.addInitParameter("resetEnable","false");
        return bean;
    }

    /**
     * 配置druid的filter
     * @return
     */
    @Bean
    public FilterRegistrationBean druidFilter(){
        FilterRegistrationBean bean=new FilterRegistrationBean();
        //指定filter
        bean.setFilter(new WebStatFilter());
        //指定filter拦截的路径 拦截所有包括jsp页面 /是不包括jsp页面
        bean.setUrlPatterns(Arrays.asList("/*"));
        bean.addInitParameter("exclusions","*.js,*.css,*.gif,*.jpg,*.png,*.ico,/druid/*");
        return bean;
    }
}
