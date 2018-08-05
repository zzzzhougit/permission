package com.yaozhou.permission.mapper;

import com.yaozhou.permission.model.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * 获取所有的role
     * @return
     */
    public List<SysRole> getAll();

    /**
     * 根据name统计role个数
     * @param name
     * @param roleId 如果不为null, 排除这个role
     * @return
     */
    public int countByRoleNameAndRoleId(@Param("name") String name, @Param("roleId") Integer roleId);

}