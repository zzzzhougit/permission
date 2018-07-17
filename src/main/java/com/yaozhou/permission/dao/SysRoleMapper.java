package com.yaozhou.permission.dao;

import com.yaozhou.permission.model.SysRole;

public interface SysRoleMapper {
    int insert(SysRole record);

    int insertSelective(SysRole record);
}