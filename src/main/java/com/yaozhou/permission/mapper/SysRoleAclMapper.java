package com.yaozhou.permission.mapper;

import com.yaozhou.permission.model.SysRoleAcl;

public interface SysRoleAclMapper {
    int deleteByPrimaryKey(Integer roleAclId);

    int insert(SysRoleAcl record);

    int insertSelective(SysRoleAcl record);

    SysRoleAcl selectByPrimaryKey(Integer roleAclId);

    int updateByPrimaryKeySelective(SysRoleAcl record);

    int updateByPrimaryKey(SysRoleAcl record);
}