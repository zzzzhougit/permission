package com.yaozhou.permission.service;

import com.yaozhou.permission.common.exception.PermException;
import com.yaozhou.permission.common.message.entity.CodeMessage;
import com.yaozhou.permission.common.message.entity.ExceptionEntity;
import com.yaozhou.permission.dao.SysDeptMapper;
import com.yaozhou.permission.model.SysDept;
import com.yaozhou.permission.params.DeptParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yaozhou.permission.common.message.entity.ExceptionEntity.*;

/**
 * @author Yao.Zhou
 * @since 2018/7/21 15:48
 */
@Service
public class SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMappr;

    public void save(DeptParam deptParam) throws Exception {
        if (exist(deptParam.getParentId(), deptParam.getName(), deptParam.getDeptId())) {
            throw new PermException(CodeMessage.create(CODE_RESOURCE_CONFLICT, "同一层级下存在相同名称的部门"));
        }

        SysDept sysDept = SysDept
                .builder()
                .seq(deptParam.getSeq())
                .name(deptParam.getName())
                .parentId(deptParam.getParentId())
                .remark(deptParam.getRemark())
                .build();

        SysDept parentSysDept = sysDeptMappr.selectByPrimaryKey(deptParam.getParentId());

    }

    //===================================

    /**
     * 检查部门是否存在
     * @param parentId
     * @param deptName
     * @param deptId
     * @return
     */
    private boolean exist(Integer parentId, String deptName, Integer deptId) {
        //TODO

        return true;
    }

}
