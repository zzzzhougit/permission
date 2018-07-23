package com.yaozhou.permission.params;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录参数
 * @author Yao.Zhou
 * @since 2018/7/22 21:01
 */
@Getter
@Setter
@ToString
public class LoginUserParam {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

}
