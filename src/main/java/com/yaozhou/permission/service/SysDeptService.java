package com.yaozhou.permission.service;

import com.yaozhou.permission.params.DeptParam;

/**
 * @author Yao.Zhou
 * @since 2018/7/21 20:52
 */
public interface SysDeptService {

    /**
     * 保存一个部门
     * @param deptParam
     * @throws Exception
     */
    public void add(DeptParam deptParam) throws Exception;

}
