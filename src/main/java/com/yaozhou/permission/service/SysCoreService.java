package com.yaozhou.permission.service;

import com.yaozhou.permission.model.SysAcl;
import com.yaozhou.permission.status.StatusCode;

import java.util.List;

/**
 * SysRoleAclService 和 SysRoleUserService的关联服务
 *
 * @author Yao.Zhou
 * @since 2018/8/5 15:40
 */
public interface SysCoreService extends StatusCode {

    /**
     * 获得用户的权限点列表
     * @param userId
     * @return
     */
    public List<SysAcl> getUserAclList(int userId);

    /**
     * 获得角色的权限点列表
     * @param roleId
     * @return
     */
    public List<SysAcl> getRoleAclList(int roleId);

}
