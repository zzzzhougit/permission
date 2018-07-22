package com.yaozhou.permission.params;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Yao.Zhou
 * @since 2018/7/18 0:28
 */
@Getter
@Setter
@ToString
public class DeptParam {

    private Integer deptId;

    private Integer parentId;

    @NotBlank(message = "部门名称不能为空")
    @Size(max = 15, min = 2, message = "部门名称需要在2-15个字之间")
    private String name;

    @NotNull(message = "展示顺序不能为空")
    private Integer seq;

    @Size(max = 150, message = "备注长度不能超过150个字")
    private String remark;

}
