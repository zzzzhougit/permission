package com.yaozhou.permission.service.impl;

import com.yaozhou.permission.mapper.SysRoleAclMapper;
import com.yaozhou.permission.mapper.SysRoleUserMapper;
import com.yaozhou.permission.model.SysAcl;
import com.yaozhou.permission.service.SysAclService;
import com.yaozhou.permission.service.SysCoreService;
import com.yaozhou.permission.service.SysRoleAclService;
import com.yaozhou.permission.service.SysRoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * SysRoleAclService 和 SysRoleUserService的关联服务
 * @author Yao.Zhou
 * @since 2018/8/5 15:41
 */
@Service
public class SysCoreServiceImpl implements SysCoreService {

    @Autowired
    private SysAclService sysAclService;
    @Autowired
    private SysRoleAclService sysRoleAclService;
    @Autowired
    private SysRoleUserService sysRoleUserService;

    @Override
    public List<SysAcl> getUserAclList(int userId) {
        if (superAdmin(userId)) {

            return sysAclService.getAll();
        }

        List<Integer> userRoleIdList = sysRoleUserService.getRoleIdListByUserId(userId);
        if (CollectionUtils.isEmpty(userRoleIdList)) {

            return new LinkedList<>();
        }

        List<Integer> userAclIdList = sysRoleAclService.getAclIdListByRoleIdList(userRoleIdList);
        if (CollectionUtils.isEmpty(userAclIdList)) {

            return new LinkedList<>();
        }

        return sysAclService.getAclListByAclIdList(userAclIdList);
    }

    @Override
    public List<SysAcl> getRoleAclList(int roleId) {
        List<Integer> roleAclIdList = sysRoleAclService.getAclIdListByRoleIdList(Arrays.asList(roleId));
        if (CollectionUtils.isEmpty(roleAclIdList)) {

            return new ArrayList<>();
        }

        return sysAclService.getAclListByAclIdList(roleAclIdList);
    }

    //====================================================

    /**
     * 判断是否是超级管理员
     * @param userId
     * @return
     */
    public boolean superAdmin(int userId) {

        return true;
    }

}
