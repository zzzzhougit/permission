package com.yaozhou.permission.service;

import com.yaozhou.permission.model.SysUser;
import com.yaozhou.permission.status.StatusCode;

import java.util.List;

/**
 * @author Yao.Zhou
 * @since 2018/8/5 20:46
 */
public interface SysRoleAclService extends StatusCode {

    /**
     * 根据一组roleId查询对应的aclId集合
     * @param roleIdList
     * @return
     */
    public List<Integer> getAclIdListByRoleIdList(List<Integer> roleIdList);

    /**
     * 更新角色下的权限点
     * @param roleId
     * @param aclIdList
     * @param operator
     * @param ipAddr
     */
    public void updateRoleAcls(int roleId, List<Integer> aclIdList, SysUser operator, String ipAddr);

}
