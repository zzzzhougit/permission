package com.yaozhou.permission.mapper;

import com.yaozhou.permission.model.SysRoleAcl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleAclMapper {
    int deleteByPrimaryKey(Integer roleAclId);

    int insert(SysRoleAcl record);

    int insertSelective(SysRoleAcl record);

    SysRoleAcl selectByPrimaryKey(Integer roleAclId);

    int updateByPrimaryKeySelective(SysRoleAcl record);

    int updateByPrimaryKey(SysRoleAcl record);

    /**
     * 根据角色列表查询权限点列表
     *
     * @param roleIdList
     * @return
     */
    public List<Integer> getAclIdListByRoleIdList(@Param("roleIdList") List<Integer> roleIdList);

    /**
     * 批量插入SysRoleAcl
     * @param sysRoleAclList
     */
    public void bathInsertSelective(@Param("sysRoleAclList") List<SysRoleAcl> sysRoleAclList);

    /**
     * 删除roleId下的acl
     *
     * @param roleId
     */
    public void deleteByRoleId(@Param("roleId") int roleId);

}