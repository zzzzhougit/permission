package com.yaozhou.permission.dao;

import com.yaozhou.permission.model.SysAcl;

public interface SysAclMapper {
    int insert(SysAcl record);

    int insertSelective(SysAcl record);
}