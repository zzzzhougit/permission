package com.yaozhou.permission.service;

import com.yaozhou.permission.dao.SysDeptMapper;
import com.yaozhou.permission.params.DeptParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new Exception();
        }
    }

    //===================================

    private boolean exist(Integer parentId, String deptName, Integer deptId) {
        //TODO

        return true;
    }

}
