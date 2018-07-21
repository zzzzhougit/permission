package com.yaozhou.permission.service;

import com.yaozhou.permission.dto.DeptLevelDto;

import java.util.List;

/**
 * @author Yao.Zhou
 * @since 2018/7/21 20:51
 */
public interface SysTreeService {

    /**
     * 获得部门树
     * @return
     * @throws Exception
     */
    public List<DeptLevelDto> deptTree() throws Exception;

}
