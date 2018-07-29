package com.yaozhou.permission.params;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

/**
 * @author Yao.Zhou
 * @since 2018/7/22 16:49
 */
@Getter
@Setter
@ToString
public class UserParam {

    private Integer userId;

    @NotBlank(message = "用户名不能为空")
    @Size(max = 20, message = "用户名长度在20个字以内")
    private String username;

    @NotBlank(message = "电话号码不能为空")
    @Size(min = 1, max = 13, message = "电话号码长度在1-13字符以内")
    private String telephone;

    @NotBlank(message = "邮箱不能为空")
    @Size(min = 5, max = 50, message = "邮箱长度在5-50字符以内")
    private String mail;

    @NotNull(message = "用户所在部门不能为空")
    private Integer deptId;

    @NotNull(message = "用户状态不能为空")
    @Min(value = 0, message = "用户状态不合法")
    @Max(value = 2, message = "用户状态不合法")
    private Short status;

    @Size(max = 200, message = "备注长度必须在200个字以内")
    private String remark;

}
