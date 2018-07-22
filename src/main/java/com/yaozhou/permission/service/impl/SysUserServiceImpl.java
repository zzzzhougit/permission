package com.yaozhou.permission.service.impl;

import com.google.common.hash.Hashing;
import com.yaozhou.permission.common.exception.PermException;
import com.yaozhou.permission.mapper.SysUserMapper;
import com.yaozhou.permission.model.SysDept;
import com.yaozhou.permission.model.SysUser;
import com.yaozhou.permission.params.UserParam;
import com.yaozhou.permission.service.SysDeptService;
import com.yaozhou.permission.service.SysUserService;
import com.yaozhou.permission.util.PassWordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

/**
 * @author Yao.Zhou
 * @since 2018/7/22 16:59
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    SysDeptService sysDeptService;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public void add(UserParam userParam) {
        SysUser sysUser =
                SysUser.builder()
                .mail(userParam.getMail())
                .deptId(userParam.getDeptId())
                .remark(userParam.getRemark())
                .salt(PassWordUtil.randomSalt())
                .username(userParam.getUsername())
                .telephone(userParam.getTelephone())
                //TODO
                .operator("System")
                .operateIp("127.0.0.1")
                .build();

        //设置密码
        String password = PassWordUtil.randomPassword();
        sysUser.setPassword(PassWordUtil.encodePassword(password, sysUser.getSalt()));

        //TODO 发送email

        addUser(sysUser);
    }

    @Override
    public void update(UserParam userParam) {
        SysUser after = SysUser.builder()
                .mail(userParam.getMail())
                .userId(userParam.getUserId())
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

        updateUser(after);
    }

    @Override
    public SysUser selectByUsername(String username) {
        return null;
    }

    //============================================

    /**
     * 在一个事务中添加用户
     * @param sysUser
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    private void addUser(SysUser sysUser) {
        checkDept(sysUser);
        checkDuplicateProperties(sysUser);
        sysUserMapper.insertSelective(sysUser);
    }

    /**
     * 在一个事务中更新用户
     * @param after
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    private void updateUser(SysUser after) {
        SysUser before = sysUserMapper.selectByPrimaryKey(after.getUserId());
        if (null == before) {
            throw new PermException(CODE_RESOURCE_NOT_EXIST, "用户不存在");
        }

        checkDept(after);
        checkDuplicateProperties(after);
        sysUserMapper.updateByPrimaryKeySelective(after);
    }

    /**
     * 检查用户部门
     * @param after
     */
    private void checkDept(SysUser after) throws PermException {
        if (after.getDeptId() != null) {
            SysDept sysDept = sysDeptService.selectByPrimaryKey(after.getDeptId());
            if (null == sysDept) {
                throw new PermException(CODE_RESOURCE_NOT_EXIST, "部门不存在");
            }
        }
    }

    /**
     * 新增,修改之前: 检查用户属性是否合理 (telephone, mail) </br></br>
     *
     * 对于更新的用户, userId始终为空 </br>
     * 对于添加的用户, userId始终不为空 </br>
     * 如果userId不为空, 验证用户唯一属性约束时排除本身属性
     *
     * @param after 将被更新的用户
     */
    private void checkDuplicateProperties(SysUser after) throws PermException {
        List<SysUser> sysUsers = sysUserMapper.fetchSysUserBySameProperties(after);
        if (sysUsers.size() > 0) {
            for (SysUser sysUser : sysUsers) {
                if (sysUser.getTelephone().equals(after.getTelephone())) {

                    throw new PermException(CODE_RESOURCE_CONFLICT, "电话号码已被使用");
                } else if (sysUser.getMail().equals(after.getMail())) {

                    throw new PermException(CODE_RESOURCE_CONFLICT, "邮箱已被使用");
                } else if(sysUser.getUsername().equals(after.getUsername())) {

                    throw new PermException(CODE_RESOURCE_CONFLICT, "用户名已被使用");
                }
            }
        } //end outer if
    }

}
