package com.yaozhou.permission.service.impl;

import com.yaozhou.permission.mapper.SysRoleUserMapper;
import com.yaozhou.permission.service.SysRoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Yao.Zhou
 * @since 2018/8/5 20:48
 */
@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public List<Integer> getRoleIdListByUserId(int userId) {
        return sysRoleUserMapper.getRoleIdListByUserId(userId);
    }
}
