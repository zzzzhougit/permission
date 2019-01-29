package com.yaozhou.permission.controllers;

import com.yaozhou.permission.common.result.Result;
import com.yaozhou.permission.params.LoginUserParam;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Api(
        value = "登录",
        tags = "用户登录",
        description = "登录相关接口",
        authorizations = {
                @Authorization("Yao.Zhou")
        }
)
@Slf4j
@Controller
@RequestMapping(path = "/login")
public class LoginController extends BaseController {

    @ApiOperation(
            hidden = true,
            value = "登录视图",
            notes = "提供一个refer, 登录完成后跳转到该refer"
    )
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

    @ApiOperation(
            value = "登录认证",
            notes = "调用该接口进行登录, 返回登录结果"
    )
    @ApiResponses(
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "登录成功", response = Result.class)
    )
    @ResponseBody
    @RequestMapping(path = "do", method = POST)
    public Result<?> doLogin(@Valid @RequestBody LoginUserParam loginUserParam,
                             HttpServletResponse response) throws Exception {
        authService.login(loginUserParam, response);

        return Result.success(null);
    }

}
