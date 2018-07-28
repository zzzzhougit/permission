package com.yaozhou.permission.filters;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Yao.Zhou
 * @since 2018/7/18 0:14
 */
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface NeedLogin {
}
