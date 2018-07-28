package com.yaozhou.permission.service;

import com.yaozhou.permission.params.LoginUserParam;
import com.yaozhou.permission.status.StatusCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录认证服务
 * @author Yao.Zhou
 * @since 2018/7/28 21:00
 */
public interface AuthService extends StatusCode {

    /**
     * 校验用户登录, 校验成功后保持登录
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public boolean keepAlive(HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 用户登录
     *
     * 1. 设置缓存
     * 2. 设置cookie
     *
     * @param loginUserParam
     * @param response
     * @throws Exception
     */
    public void login(LoginUserParam loginUserParam, HttpServletResponse response) throws Exception;

    /**
     * 用户登出
     * @param request
     * @param response
     * @throws Exception
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
