package com.yaozhou.permission.controllers.sys;

import com.yaozhou.permission.common.message.Result;
import com.yaozhou.permission.controllers.BaseController;
import com.yaozhou.permission.interceptors.NeedLogin;
import com.yaozhou.permission.params.DeptParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @author Yao.Zhou
 * @since 2018/7/18 0:34
 */
@Controller
@NeedLogin
@RequestMapping(path = "/sys/dept")
public class SysDeptController extends BaseController {

    @ResponseBody
    @RequestMapping(path = "/save")
    public Result<?> saveDept(@Valid DeptParam deptParam) {

        return null;
    }

}
