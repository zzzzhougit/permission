package com.yaozhou.permission.mapper;

import com.yaozhou.permission.model.SysAcl;
import com.yaozhou.permission.params.PageParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAclMapper {
    int deleteByPrimaryKey(Integer aclId);

    int insert(SysAcl record);

    int insertSelective(SysAcl record);

    SysAcl selectByPrimaryKey(Integer aclId);

    int updateByPrimaryKeySelective(SysAcl record);

    int updateByPrimaryKey(SysAcl record);

    /**
     * 根据AclId列表获取Acl详情列表
     * @param aclIdList
     * @return
     */
    public List<SysAcl> getAclListByAclIdList(@Param("aclIdList") List<Integer> aclIdList);

    /**
     * 获得所有的权限点
     * @return
     */
    public List<SysAcl> getAll();

    /**
     * 统计AclModule下相同名字的Acl个数
     * @param aclModuleId
     * @param name
     * @param aclId 如果不为null, 排除这个Acl
     * @return
     */
    public int coutByAclModuleIdAndName(@Param("aclModuleId") int aclModuleId, @Param("name") String name, @Param("aclId") Integer aclId);

    /**
     * 统计AclModule下的Acl个数
     * @param aclModuleId
     * @return
     */
    public int countByAclModuleId(@Param("aclModuleId") int aclModuleId);

    /**
     * 分页获取AclModule下的acl
     * @param aclModuleId
     * @param pageParam
     * @return
     */
    List<SysAcl> getPageByAclModuleId(@Param("aclModuleId") int aclModuleId, @Param("pageParam")PageParam pageParam);

    /**
     * 统计AclModule下相同名字的个数
     * @param aclModuleId
     * @param name
     * @param url
     * @param aclId 当aclId!=null时, aclId不在统计范围内
     * @return
     */
    public int countByNameAndAclModuleId(@Param("aclModuleId") int aclModuleId, @Param("name") String name, @Param("url")String url, @Param("aclId") Integer aclId);

}