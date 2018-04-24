package com.dity.argument.config;

import com.dity.argument.component.PersonArgumentResolver;
import com.dity.argument.component.PersonHttpMessageConverter;
import com.dity.argument.component.PersonReturnValueResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * @author:yuhang
 * @Date:2018/4/23
 */
@Configuration
public class ArgumentWebConfig extends WebMvcConfigurationSupport {
    /**
     * 增加参数解析器
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new PersonArgumentResolver());
    }

    /**
     * 增加返回解析器
     * @param returnValueHandlers
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        super.addReturnValueHandlers(returnValueHandlers);
        returnValueHandlers.add(new PersonReturnValueResolver());
    }

    /**
     * 扩展HttpMessageResolver
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);
        converters.add(new PersonHttpMessageConverter());
    }




}
