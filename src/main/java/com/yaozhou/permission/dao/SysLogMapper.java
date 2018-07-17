package com.yaozhou.permission.dao;

import com.yaozhou.permission.model.SysLogWithBLOBs;

public interface SysLogMapper {
    int insert(SysLogWithBLOBs record);

    int insertSelective(SysLogWithBLOBs record);
}