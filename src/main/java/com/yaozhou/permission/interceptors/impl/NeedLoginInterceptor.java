package com.yaozhou.permission.interceptors.impl;

import com.yaozhou.permission.interceptors.NeedLogin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;

/**
 * @author Yao.Zhou
 * @since 2018/7/18 0:15
 * @see com.yaozhou.permission.interceptors.NeedLogin
 */
public class NeedLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (needsIntercept(handler)) {

        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (needsIntercept(handler)) {

        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (needsIntercept(handler)) {

        }
    }

    private static boolean needsIntercept(Object handler) {
        Annotation annotation = handler.getClass().getAnnotation(NeedLogin.class);
        if (null == annotation) {

            return false;
        }

        return true;
    }

}
