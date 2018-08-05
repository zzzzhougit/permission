package com.yaozhou.permission.service;

import com.yaozhou.permission.model.SysDept;
import com.yaozhou.permission.model.SysUser;
import com.yaozhou.permission.params.DeptParam;
import com.yaozhou.permission.status.StatusCode;

import java.util.List;

/**
 * @author Yao.Zhou
 * @since 2018/7/21 20:52
 */
public interface SysDeptService extends StatusCode {

    /**
     * 获得所有部门
     * @return
     */
    public List<SysDept> getAll();

    /**
     * 保存一个部门
     * @param deptParam
     * @param operator
     * @param ipaddr
     * @throws Exception
     */
    public void add(DeptParam deptParam, SysUser operator, String ipaddr) throws Exception;

    /**
     * 更新一个部门
     * @param deptParam
     * @param operator
     * @param ipaddr
     * @throws Exception
     */
    public void update(DeptParam deptParam, SysUser operator, String ipaddr) throws Exception;

    /**
     * 根据deptId查询部门
     * @param deptId
     * @return
     */
    public SysDept selectByPrimaryKey(int deptId);

}
