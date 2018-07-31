package com.yaozhou.permission.service;

import com.yaozhou.permission.common.exception.PermException;
import com.yaozhou.permission.model.SysUser;
import com.yaozhou.permission.params.AclModuleParam;
import com.yaozhou.permission.status.StatusCode;

/**
 * @author Yao.Zhou
 * @since 2018/7/31 18:22
 */
public interface SysAclModuleService extends StatusCode {

    /**
     * 添加权限模块
     * @param aclModuleParam
     * @param operator
     * @param ipaddr
     */
    public void add(AclModuleParam aclModuleParam, SysUser operator, String ipaddr);

    /**
     * 更新权限模块
     * @param aclModuleParam
     * @param operator
     * @param ipaddr
     */
    public void update(AclModuleParam aclModuleParam, SysUser operator, String ipaddr) throws PermException;

}
