package com.yaozhou.permission.service;

import com.yaozhou.permission.dto.AclModuleLevelDto;
import com.yaozhou.permission.dto.DeptLevelDto;
import com.yaozhou.permission.status.StatusCode;

import java.util.List;

/**
 * @author Yao.Zhou
 * @since 2018/7/21 20:51
 */
public interface SysTreeService extends StatusCode {

    /**
     * 获得部门树
     * @return
     * @throws Exception
     */
    public List<DeptLevelDto> deptTree() throws Exception;

    /**
     * 获得权限模块树
     * @return
     * @throws Exception
     */
    public List<AclModuleLevelDto> aclModuleTree() throws Exception;

    /**
     *
     * @param userId
     * @param roleId
     * @return
     * @throws Exception
     */
    public List<AclModuleLevelDto> roleTree(int userId, int roleId) throws Exception;

}
