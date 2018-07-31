package com.yaozhou.permission.params;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

/**
 * @author Yao.Zhou
 * @since 2018/7/31 18:13
 */
@Getter
@Setter
@ToString
public class AclModuleParam {

    private Integer aclModuleId;

    private Integer parentId;

    @NotBlank(message = "权限模块名称不能为空")
    @Size(min = 2, max = 20, message = "权限模块名称长度必须在2-20个字之间")
    private String name;

    @NotNull(message = "权限模块展示顺序不能为空")
    private Integer seq;

    @Size(max = 200, message = "权限模块备注必须在200个字以内")
    private String remark;

    @Min(value = 0, message = "权限模块状态不合法")
    @Max(value = 1, message = "权限模块状态不合法")
    @NotNull(message = "权限模块状态不能为空")
    private Short status;

}
