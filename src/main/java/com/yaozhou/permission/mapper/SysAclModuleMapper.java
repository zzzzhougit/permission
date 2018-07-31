package com.yaozhou.permission.mapper;

import com.yaozhou.permission.model.SysAclModule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAclModuleMapper {
    int deleteByPrimaryKey(Integer aclModuleId);

    int insert(SysAclModule record);

    int insertSelective(SysAclModule record);

    SysAclModule selectByPrimaryKey(Integer aclModuleId);

    int updateByPrimaryKeySelective(SysAclModule record);

    int updateByPrimaryKey(SysAclModule record);

    /**
     * 查询一个权限模块下具有相同名称的权限模块个数
     * @param parentId
     * @param name
     * @param aclModuleId
     * @return
     */
    public int countByNameAndParentId(@Param("parentId") Integer parentId, @Param("name") String name, @Param("aclModuleId") Integer aclModuleId);

    /**
     * 根据Level值查询所有子权限模块
     * @param level
     * @return
     */
    public List<SysAclModule> getChildrenDeptByLevel(@Param("level") String level);

    /**
     * 批量更新权限模块
     * @param sysAclModules
     */
    public void batchUpdateByPrimaryKeySelective(@Param("aclModels") List<SysAclModule> sysAclModules);

    /**
     * 获得所有的权限模块
     * @return
     */
    public List<SysAclModule> getAllAclModule();

}