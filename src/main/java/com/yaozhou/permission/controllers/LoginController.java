package com.yaozhou.permission.controllers;

import com.yaozhou.permission.common.result.Result;
import com.yaozhou.permission.params.LoginUserParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author Yao.Zhou
 * @since 2018/7/22 20:55
 */
@Slf4j
@Controller
@RequestMapping(path = "/login")
public class LoginController extends BaseController {

    /**
     * view for user login
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(method = {GET})
    public Object loginView(HttpServletRequest request, HttpServletResponse response) {

        return null;
    }

    /**
     * do login
     * @param loginUserParam
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "dologin", method = {GET, POST})
    public Result<?> doLogin(@Valid LoginUserParam loginUserParam,
                             HttpServletRequest request, HttpServletResponse response) {
        //sysUserService

        return Result.success(null);
    }

}
