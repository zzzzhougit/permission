package com.yaozhou.permission.interceptors.impl;

import com.yaozhou.permission.interceptors.NeedLogin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author Yao.Zhou
 * @since 2018/7/18 0:15
 * @see com.yaozhou.permission.interceptors.NeedLogin
 */
public class NeedLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!needLoginPresent(handler)) {
            return true;
        }

        //TODO

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (!needLoginPresent(handler)) {
            return ;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (!needLoginPresent(handler)) {
            return ;
        }
    }

    private static boolean needLoginPresent(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        Method method = handlerMethod.getMethod();
        Class<?> clazz = handlerMethod.getMethod().getDeclaringClass();
        if (method.isAnnotationPresent(NeedLogin.class) || clazz.isAnnotationPresent(NeedLogin.class)) {

            return true;
        }

        return false;
    }

}
