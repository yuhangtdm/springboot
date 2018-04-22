package com.dity.web.config;

import com.dity.web.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:yuhang
 * @Date:2018/4/21
 */
@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/*").excludePathPatterns("/user/login","/login");
        registry.addInterceptor(loggerInterceptor()).addPathPatterns("/**");
    }
    class SessionInterceptor implements HandlerInterceptor{

        @Override
        public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
            System.out.println(httpServletRequest.getRequestURI());
            User user= (User) httpServletRequest.getSession().getAttribute("user");
            if(user!=null){
                return true;
            }
            httpServletResponse.sendRedirect("/login");
            return false;
        }

        @Override
        public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        }

        @Override
        public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

        }
    }

    @Bean
    public LoggerInterceptor loggerInterceptor(){
        return new LoggerInterceptor();
    }

}
