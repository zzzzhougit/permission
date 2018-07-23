package com.yaozhou.permission.mapper;

import com.yaozhou.permission.model.SysLog;
import com.yaozhou.permission.model.SysLogWithBLOBs;

public interface SysLogMapper {
    int deleteByPrimaryKey(Integer logId);

    int insert(SysLogWithBLOBs record);

    int insertSelective(SysLogWithBLOBs record);

    SysLogWithBLOBs selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(SysLogWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysLogWithBLOBs record);

    int updateByPrimaryKey(SysLog record);
}