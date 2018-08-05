package com.yaozhou.permission.service;

import com.yaozhou.permission.model.SysRole;
import com.yaozhou.permission.model.SysUser;
import com.yaozhou.permission.params.RoleParam;
import com.yaozhou.permission.status.StatusCode;

import java.util.List;

/**
 * @author Yao.Zhou
 * @since 2018/8/5 13:05
 */
public interface SysRoleService extends StatusCode {

    /**
     * 新增角色
     * @param roleParam
     * @param operator
     * @param ipaddr
     */
    public void add(RoleParam roleParam, SysUser operator, String ipaddr);

    /**
     * 更新角色
     * @param roleParam
     * @param operator
     * @param ipaddr
     */
    public void update(RoleParam roleParam, SysUser operator, String ipaddr);

    /**
     * 删除角色
     * @param roleId
     * @param operator
     * @param ipaddr
     */
    public void delete(int roleId, SysUser operator, String ipaddr);

    /**
     * 获取所有的role
     * @return
     */
    public List<SysRole> getAll();

}
