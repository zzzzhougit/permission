package com.yaozhou.permission.dao;

import com.yaozhou.permission.model.SysRoleAcl;

public interface SysRoleAclMapper {
    int insert(SysRoleAcl record);

    int insertSelective(SysRoleAcl record);
}