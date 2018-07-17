package com.yaozhou.permission.dao;

import com.yaozhou.permission.model.SysRoleUser;

public interface SysRoleUserMapper {
    int insert(SysRoleUser record);

    int insertSelective(SysRoleUser record);
}