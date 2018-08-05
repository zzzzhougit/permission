package com.yaozhou.permission.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.yaozhou.permission.mapper.SysRoleAclMapper;
import com.yaozhou.permission.model.SysRoleAcl;
import com.yaozhou.permission.model.SysUser;
import com.yaozhou.permission.service.SysRoleAclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Yao.Zhou
 * @since 2018/8/5 20:46
 */
@Service
public class SysRoleAclServiceImpl implements SysRoleAclService {

    @Autowired
    private SysRoleAclMapper sysRoleAclMapper;

    @Override
    public List<Integer> getAclIdListByRoleIdList(List<Integer> roleIdList) {
        if (CollectionUtils.isEmpty(roleIdList)) {

            return new ArrayList<>();
        }

        return sysRoleAclMapper.getAclIdListByRoleIdList(roleIdList);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void updateRoleAcls(int roleId, List<Integer> aclIdList, SysUser operator, String ipAddr) {
        List<Integer> before = sysRoleAclMapper.getAclIdListByRoleIdList(Arrays.asList(roleId));
        if (before.size() == aclIdList.size()) {
            //如果更新前后一致, 不更新
            Set<Integer> beforeSet = Sets.newHashSet(before);
            Set<Integer> afterSet = Sets.newHashSet(aclIdList);
            before.removeAll(afterSet);
            if (before.size() == 0) {

                return ;
            }
        }

        //先删除role下的acl, 再写入新的
        sysRoleAclMapper.deleteByRoleId(roleId);
        if (!CollectionUtils.isEmpty(aclIdList)) {
            List<SysRoleAcl> sysRoleAclList = Lists.newLinkedList();
            aclIdList.forEach(aclId -> {
                SysRoleAcl sysRoleAcl = SysRoleAcl.builder()
                        .aclId(aclId)
                        .roleId(roleId)
                        .operateIp(ipAddr)
                        .operator(operator.getUsername())
                        .build();

                sysRoleAclList.add(sysRoleAcl);
            });

            //insert
            sysRoleAclMapper.bathInsertSelective(sysRoleAclList);
        }
    }

}
