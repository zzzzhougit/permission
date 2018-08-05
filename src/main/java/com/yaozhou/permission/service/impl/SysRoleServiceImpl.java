package com.yaozhou.permission.service.impl;

import com.yaozhou.permission.common.exception.PermException;
import com.yaozhou.permission.mapper.SysRoleMapper;
import com.yaozhou.permission.model.SysRole;
import com.yaozhou.permission.model.SysUser;
import com.yaozhou.permission.params.RoleParam;
import com.yaozhou.permission.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Yao.Zhou
 * @since 2018/8/5 13:06
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void add(RoleParam roleParam, SysUser operator, String ipaddr) {
        SysRole sysRole = SysRole.builder()
                .operateIp(ipaddr)
                .type(roleParam.getType())
                .name(roleParam.getName())
                .remark(roleParam.getRemark())
                .status(roleParam.getStatus())
                .operator(operator.getUsername())
                .build();

        if (exist(roleParam.getName(), roleParam.getRoleId())) {
            throw new PermException(CODE_RESOURCE_CONFLICT, "已存在相同的Role");
        }

        sysRoleMapper.insertSelective(sysRole);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(RoleParam roleParam, SysUser operator, String ipaddr) {
        SysRole before = sysRoleMapper.selectByPrimaryKey(roleParam.getRoleId());
        if (null == before) {
            throw new PermException(CODE_RESOURCE_NOT_EXIST, "角色不存在");
        }

        SysRole after = SysRole.builder()
                .operateIp(ipaddr)
                .type(roleParam.getType())
                .name(roleParam.getName())
                .roleId(roleParam.getRoleId())
                .remark(roleParam.getRemark())
                .status(roleParam.getStatus())
                .operator(operator.getUsername())
                .build();

        if (exist(roleParam.getName(), roleParam.getRoleId())) {
            throw new PermException(CODE_RESOURCE_CONFLICT, "已存在相同的Role");
        }

        sysRoleMapper.updateByPrimaryKeySelective(after);
    }

    @Override
    public void delete(int roleId, SysUser operator, String ipaddr) {

    }

    @Override
    public List<SysRole> getAll() {
        return sysRoleMapper.getAll();
    }

    //================================================

    /**
     * 判断是否有重复
     * @param name
     * @param roleId
     * @return
     */
    private boolean exist(String name, Integer roleId) {
        return sysRoleMapper.countByRoleNameAndRoleId(name, roleId) > 0;
    }

}
