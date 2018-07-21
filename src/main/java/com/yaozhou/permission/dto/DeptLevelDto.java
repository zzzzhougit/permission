package com.yaozhou.permission.dto;

import com.yaozhou.permission.model.SysDept;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * 每一个DeptLevelDto代表一个SysDept </br>
 * 每一个nextLevelDeptList包含该SysDept下一级的所有DeptLevelDto </br>
 *
 * @author Yao.Zhou
 * @since 2018/7/21 19:58
 */
@Getter
@Setter
@ToString
public class DeptLevelDto extends SysDept {

    private List<DeptLevelDto> nextLevelDeptList = new LinkedList<>();

    public static DeptLevelDto adapt(SysDept sysDept) {
        DeptLevelDto deptLevelDto = new DeptLevelDto();
        BeanUtils.copyProperties(deptLevelDto, sysDept);

        return deptLevelDto;
    }

}
