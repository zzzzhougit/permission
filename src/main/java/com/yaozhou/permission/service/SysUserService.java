package com.yaozhou.permission.service;

import com.yaozhou.permission.model.PageResult;
import com.yaozhou.permission.model.SysUser;
import com.yaozhou.permission.params.PageParam;
import com.yaozhou.permission.params.UserParam;
import com.yaozhou.permission.status.StatusCode;

/**
 * @author Yao.Zhou
 * @since 2018/7/22 16:58
 */
public interface SysUserService extends StatusCode {

    /**
     * 根据用户id获取用户
     * @param userId
     * @return
     */
    public SysUser cachedSelectByPrimaryKey(int userId);

    /**
     * 添加用户
     * @param userParam
     */
    public void add(UserParam userParam);

    /**
     * 更新用户
     *
     * 更新用户后：
     *  当用户状态不合法, 移除用户缓存数据;
     *  当用户状态合法, 如果缓存数据存在, 更新缓存数据
     *
     *  用户更新和缓存更新不在一个事务内
     *
     * @param userParam
     */
    public void update(UserParam userParam);

    /**
     * 根据用户名获得用户
     * @param username
     * @return
     */
    public SysUser selectByUserName(String username);

    /**
     * 根据deptId分页查询用户列表
     * @param deptId
     * @param pageParam
     * @return
     */
    public PageResult<SysUser> getPageByDeptId(int deptId, PageParam pageParam);

}
