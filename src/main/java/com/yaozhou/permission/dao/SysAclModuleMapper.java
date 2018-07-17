package com.yaozhou.permission.dao;

import com.yaozhou.permission.model.SysAclModule;

public interface SysAclModuleMapper {
    int insert(SysAclModule record);

    int insertSelective(SysAclModule record);
}