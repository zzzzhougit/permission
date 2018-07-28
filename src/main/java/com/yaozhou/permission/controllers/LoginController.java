package com.yaozhou.permission.controllers;

import com.yaozhou.permission.common.result.Result;
import com.yaozhou.permission.params.LoginUserParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
     * @return
     */
    @RequestMapping(method = GET)
    public Object loginView(Model model, String refer) {
        if (StringUtils.isNotBlank(refer)) {
            try {
                model.addAttribute("refer", URLEncoder.encode(refer, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return "login";
    }

    /**
     * 登录认证接口
     * @param loginUserParam
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "do", method = POST)
    public Result<?> doLogin(@Valid LoginUserParam loginUserParam, HttpServletResponse response) throws Exception {
        authService.login(loginUserParam, response);

        return Result.success(null);
    }

}
