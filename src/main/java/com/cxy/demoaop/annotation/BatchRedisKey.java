package com.cxy.demoaop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author chenxianyun
 * @Description
 * @create 2022-02-04 14:45
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BatchRedisKey {

    LockKeyType fileName();

    String value() default "";
}
