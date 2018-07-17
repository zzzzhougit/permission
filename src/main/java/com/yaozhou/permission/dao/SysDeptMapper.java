package com.yaozhou.permission.dao;

import com.yaozhou.permission.model.SysDept;

public interface SysDeptMapper {

    int insert(SysDept record);

    int insertSelective(SysDept record);
}