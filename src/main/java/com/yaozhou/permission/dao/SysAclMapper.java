package com.yaozhou.permission.dao;

import com.yaozhou.permission.model.SysAcl;

public interface SysAclMapper {
    int deleteByPrimaryKey(Integer aclId);

    int insert(SysAcl record);

    int insertSelective(SysAcl record);

    SysAcl selectByPrimaryKey(Integer aclId);

    int updateByPrimaryKeySelective(SysAcl record);

    int updateByPrimaryKey(SysAcl record);
}