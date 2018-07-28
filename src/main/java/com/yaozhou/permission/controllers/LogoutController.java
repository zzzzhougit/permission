package com.yaozhou.permission.controllers;

import com.yaozhou.permission.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 退出登录
 * @author Yao.Zhou
 * @since 2018/7/28 16:09
 */
@Slf4j
@Controller
@RequestMapping(path = "/logout")
public class LogoutController extends BaseController {

    /**
     *
     * @param refer
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "do", method = GET)
    public String doLogout(String refer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        authService.logout(request, response);

        if (StringUtils.isBlank(refer)) {
            refer = "/login";
        }

        response.sendRedirect(refer);

        return null;
    }

    /**
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(path = "do", method = POST)
    public Result<?> doLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        authService.logout(request, response);

        return Result.success(null);
    }

}
