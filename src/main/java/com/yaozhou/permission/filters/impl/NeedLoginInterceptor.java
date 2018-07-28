package com.yaozhou.permission.filters.impl;

import com.yaozhou.permission.filters.NeedLogin;
import com.yaozhou.permission.model.SysUser;
import com.yaozhou.permission.service.AuthService;
import com.yaozhou.permission.service.SysUserService;
import com.yaozhou.permission.util.CookieUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static com.yaozhou.permission.common.CookieNames.User.*;

/**
 * @author Yao.Zhou
 * @since 2018/7/18 0:15
 * @see com.yaozhou.permission.filters.NeedLogin
 */
public class NeedLoginInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!needLoginPresent(handler)) {

            return true;
        }

        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        AuthService authService = factory.getBean(AuthService.class);
        if (authService.keepAlive(request, response) == false) {
            response.sendRedirect("/login");

            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private static boolean needLoginPresent(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            Method method = handlerMethod.getMethod();
            Class<?> clazz = handlerMethod.getMethod().getDeclaringClass();
            if (method.isAnnotationPresent(NeedLogin.class) || clazz.isAnnotationPresent(NeedLogin.class)) {

                return true;
            }
        }

        return false;
    }

}
