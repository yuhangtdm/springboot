package com.dity.web.util;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:yuhang
 * @Date:2018/4/21
 */
public class SpringBeanUtils {

    /**
     * 获取spring容器中的bean
     * @param clazz
     * @param request
     * @param <T>
     * @return
     */
    public static <T> T  getDao(Class<T> clazz, HttpServletRequest request){
        BeanFactory beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        return beanFactory.getBean(clazz);
    }
}
