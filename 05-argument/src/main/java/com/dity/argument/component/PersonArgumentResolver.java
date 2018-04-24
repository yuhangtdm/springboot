package com.dity.argument.component;

import com.dity.argument.annotation.MultiPerson;
import com.dity.argument.pojo.Person;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author:yuhang
 * @Date:2018/4/23
 */
public class PersonArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(Person.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        MultiPerson annotation = methodParameter.getParameterAnnotation(MultiPerson.class);
        String first_name = nativeWebRequest.getParameter(annotation.value()+".first_name");
        String last_name = nativeWebRequest.getParameter(annotation.value()+".last_name");

        return new Person(first_name,last_name);
    }
}
