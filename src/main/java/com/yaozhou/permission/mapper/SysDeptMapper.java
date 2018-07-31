package com.yaozhou.permission.mapper;

import com.yaozhou.permission.model.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDeptMapper {

    int deleteByPrimaryKey(Integer deptId);

    int insert(SysDept record);

    int insertSelective(SysDept record);

    String selectLevelByPrimaryKey(Integer deptId);

    SysDept selectByPrimaryKey(Integer deptId);

    int updateByPrimaryKeySelective(SysDept record);

    int updateByPrimaryKey(SysDept record);

    /**
     * 查询一个部门下具有相同名称的子部门个数
     * @param parentId
     * @param name
     * @param deptId 如果deptId不为空, 排除这个deptId
     * @return
     */
    public int countByNameAndParentId(@Param("parentId") Integer parentId, @Param("name") String name, @Param("deptId") Integer deptId);

    /**
     * 查询所有的dept
     * @return
     */
    public List<SysDept> getAllDept();

    /**
     * 根据Level值查询所有子部门
     * @param level
     * @return
     */
    public List<SysDept> getChildrenDeptByLevel(@Param("level") String level);

    /**
     * 批量更新部门
     * @param sysDepts
     */
    public void batchUpdateByPrimaryKeySelective(@Param("depts") List<SysDept> sysDepts);

}