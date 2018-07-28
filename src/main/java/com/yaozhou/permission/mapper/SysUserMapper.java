package com.yaozhou.permission.mapper;

import com.yaozhou.permission.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {

    int deleteByPrimaryKey(Integer userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    /**
     * 根据用户名获得用户信息
     * @param username
     * @return
     */
    public SysUser selectByUserName(@Param("username") String username);

    /**
     * 根据用户邮箱或者手机号码查找用户集
     * 如果用户userId不为空, 排除自己
     * @param sysUser
     * @return
     */
    public List<SysUser> fetchSysUserBySameProperties(@Param("sysUser") SysUser sysUser);

}