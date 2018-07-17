package com.yaozhou.permission.dao;

import com.yaozhou.permission.model.SysUser;

public interface SysUserMapper {
    int insert(SysUser record);

    int insertSelective(SysUser record);
}