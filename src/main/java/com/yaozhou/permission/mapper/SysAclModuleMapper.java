package com.yaozhou.permission.mapper;

import com.yaozhou.permission.model.SysAclModule;

public interface SysAclModuleMapper {
    int deleteByPrimaryKey(Integer aclModuleId);

    int insert(SysAclModule record);

    int insertSelective(SysAclModule record);

    SysAclModule selectByPrimaryKey(Integer aclModuleId);

    int updateByPrimaryKeySelective(SysAclModule record);

    int updateByPrimaryKey(SysAclModule record);
}