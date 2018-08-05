package com.yaozhou.permission.params;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * @author Yao.Zhou
 * @since 2018/8/1 0:00
 */
@Getter
@Setter
@ToString
public class AclParam {

    private Integer aclId;

    @NotBlank(message = "权限点名称不能为空")
    @Length(min = 2, max = 20, message = "权限点名称必须在2-20个字之间")
    private String name;

    @NotNull(message = "权限模块id不能为空")
    private Integer aclModuleId;

    @Length(min = 6, max = 256, message = "url必须在6-256个字之间")
    private String url;

    @NotNull(message = "权限点类型不能为空")
    @Min(value = 0, message = "权限点类型不合法")
    @Max(value = 3, message = "权限点类型不合法")
    private Short type;

    @NotNull(message = "权限点状态不能为空")
    @Min(value = 0, message = "权限点状态不合法")
    @Max(value = 1, message = "权限点状态不合法")
    private Short status;

    @NotNull(message = "seq不能为空")
    private Integer seq;

    @Length(min = 0, max = 200, message = "备注必须在0-200个字以内")
    private String remark;

}
