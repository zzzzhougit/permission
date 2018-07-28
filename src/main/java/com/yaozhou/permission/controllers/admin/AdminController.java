package com.yaozhou.permission.controllers.admin;

import com.yaozhou.permission.controllers.BaseController;
import com.yaozhou.permission.filters.NeedLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
//import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Yao.Zhou
 * @since 2018/7/28 14:56
 */
@Slf4j
@NeedLogin
@Controller
@RequestMapping(path = "/admin")
public class AdminController extends BaseController {

    @RequestMapping(method = GET)
    public String index() {

        return "admin";
    }

}
