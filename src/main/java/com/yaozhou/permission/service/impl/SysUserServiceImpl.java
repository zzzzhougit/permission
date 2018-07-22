package com.yaozhou.permission.service.impl;

import com.yaozhou.permission.common.exception.PermException;
import com.yaozhou.permission.mapper.SysUserMapper;
import com.yaozhou.permission.model.SysUser;
import com.yaozhou.permission.params.UserParam;
import com.yaozhou.permission.service.SysUserService;
import com.yaozhou.permission.util.PassWordUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author Yao.Zhou
 * @since 2018/7/22 16:59
 */
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public void add(UserParam userParam) {
        SysUser sysUser =
                SysUser.builder()
                .username(userParam.getUsername())
                .telephone(userParam.getTelephone())
                .mail(userParam.getMail())
                .deptId(userParam.getDeptId())
                .remark(userParam.getRemark())
                //TODO
                .operator("System")
                .operateIp("127.0.0.1")
                .build();
        String password = PassWordUtil.randomPassword();
        sysUser.setPassword(password);

        checkExist(sysUser);
        //TODO 发送email

        sysUserMapper.insertSelective(sysUser);
    }

    @Override
    public void update(UserParam userParam) {
        SysUser sysUser = SysUser.builder()
                .mail(userParam.getMail())
                .deptId(userParam.getDeptId())
                .status(userParam.getStatus())
                .remark(userParam.getRemark())
                .username(userParam.getUsername())
                .telephone(userParam.getTelephone())
                //TODO
                .operator("System")
                .operateIp("127.0.0.1")
                .operateTime(new Date())
                .build();

        checkExist(sysUser);

        sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    //============================================

    /**
     * 新增,修改之前: 检查用户属性是否合理
     * @param afterPropertiesSet 设置属性后的用户
     */
    private void checkExist(SysUser afterPropertiesSet) throws PermException {
        List<SysUser> sysUsers = sysUserMapper.fetchSysUserBySameProperties(afterPropertiesSet);
        if (sysUsers.size() > 0) {
            for (SysUser sysUser : sysUsers) {
                if (sysUser.getTelephone().equals(afterPropertiesSet.getTelephone())) {

                    throw new PermException(CODE_RESOURCE_CONFLICT, "电话号码已被使用");
                } else if (sysUser.getMail().equals(afterPropertiesSet.getMail())) {

                    throw new PermException(CODE_RESOURCE_CONFLICT, "邮箱已被使用");
                }
            }
        } //end outer if
    }

}
