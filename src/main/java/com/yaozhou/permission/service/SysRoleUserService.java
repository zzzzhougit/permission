package com.yaozhou.permission.service;

import com.yaozhou.permission.status.StatusCode;

import java.util.List;

/**
 * @author Yao.Zhou
 * @since 2018/8/5 20:48
 */
public interface SysRoleUserService extends StatusCode {

    /**
     * 查询某用户的所有角色id
     * @param userId
     * @return
     */
    List<Integer> getRoleIdListByUserId(int userId);

}
