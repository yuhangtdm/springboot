package com.dity.argument.annotation;

import java.lang.annotation.*;

/**
 * @author:yuhang
 * @Date:2018/4/23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
@Inherited
public @interface MultiPerson {
    public String value()default "";
}
