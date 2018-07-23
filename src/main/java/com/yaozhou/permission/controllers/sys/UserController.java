package com.yaozhou.permission.controllers.sys;

import com.yaozhou.permission.common.result.Result;
import com.yaozhou.permission.controllers.BaseController;
import com.yaozhou.permission.interceptors.NeedLogin;
import com.yaozhou.permission.params.UserParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * 用户管理
 * @author Yao.Zhou
 * @since 2018/7/22 16:49
 */
@Slf4j
@NeedLogin
@Controller
@RequestMapping(path = "/sys/user")
public class UserController extends BaseController {

    /**
     * 添加用户
     * @param userParam
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/add", method = {GET, POST})
    public Result<?> add(@Valid UserParam userParam) {
        sysUserService.add(userParam);

        return Result.success(null);
    }

    /**
     * 更新用户
     * @param userParam
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/update", method = {GET, POST})
    public Result<?> update(@Valid UserParam userParam) {
        sysUserService.update(userParam);

        return Result.success(null);
    }

}
