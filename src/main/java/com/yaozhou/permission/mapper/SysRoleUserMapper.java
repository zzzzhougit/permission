package com.yaozhou.permission.mapper;

import com.yaozhou.permission.model.SysRoleUser;

public interface SysRoleUserMapper {
    int deleteByPrimaryKey(Integer roleUserId);

    int insert(SysRoleUser record);

    int insertSelective(SysRoleUser record);

    SysRoleUser selectByPrimaryKey(Integer roleUserId);

    int updateByPrimaryKeySelective(SysRoleUser record);

    int updateByPrimaryKey(SysRoleUser record);
}