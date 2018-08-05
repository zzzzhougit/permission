package com.yaozhou.permission.service.impl;

import com.yaozhou.permission.common.exception.PermException;
import com.yaozhou.permission.mapper.SysAclModuleMapper;
import com.yaozhou.permission.model.SysAclModule;
import com.yaozhou.permission.model.SysUser;
import com.yaozhou.permission.params.AclModuleParam;
import com.yaozhou.permission.service.SysAclModuleService;
import com.yaozhou.permission.util.LevelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yao.Zhou
 * @since 2018/7/31 18:22
 */
@Service
public class SysAclModuleServiceImpl implements SysAclModuleService {

    @Autowired
    private SysAclModuleMapper sysAclModuleMapper;

    @Override
    public List<SysAclModule> getAll() {
        return sysAclModuleMapper.getAllAclModule();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void add(AclModuleParam aclModuleParam, SysUser operator, String ipaddr) {
        SysAclModule sysAclModule = SysAclModule.builder()
                .operateIp(ipaddr)
                .operateTime(new Date())
                .seq(aclModuleParam.getSeq())
                .name(aclModuleParam.getName())
                .operator(operator.getUsername())
                .status(aclModuleParam.getStatus())
                .remark(aclModuleParam.getRemark())
                .parentId(null == aclModuleParam.getParentId() ? 0 : aclModuleParam.getParentId())
                .build();
        sysAclModule.setLevel(checkAndCalculateLevel(sysAclModule));

        sysAclModuleMapper.insertSelective(sysAclModule);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(AclModuleParam aclModuleParam, SysUser operator, String ipaddr) throws PermException {
        SysAclModule before = sysAclModuleMapper.selectByPrimaryKey(aclModuleParam.getAclModuleId());
        if (null == before) {
            throw new PermException(CODE_RESOURCE_NOT_EXIST, "权限模块不存在");
        }

        SysAclModule after = SysAclModule.builder()
                .operateIp(ipaddr)
                .operateTime(new Date())
                .seq(aclModuleParam.getSeq())
                .name(aclModuleParam.getName())
                .operator(operator.getUsername())
                .status(aclModuleParam.getStatus())
                .remark(aclModuleParam.getRemark())
                .parentId(aclModuleParam.getParentId())
                .aclModuleId(aclModuleParam.getAclModuleId())
                .build();
        after.setLevel(checkAndCalculateLevel(after));

        updateAclModuleWithChild(before, after);
    }

    //=======================================================

    /**
     * 计算level
     * @param after
     * @return
     */
    private String checkAndCalculateLevel(SysAclModule after) {
        SysAclModule parent = null;
        if (null != after.getParentId() && after.getParentId() > 0) {
            parent = sysAclModuleMapper.selectByPrimaryKey(after.getParentId());
            if (null == parent) {

                throw new PermException(CODE_RESOURCE_NOT_EXIST, "上级权限模块不存在");
            } else {

                if (sysAclModuleMapper.countByNameAndParentId(after.getParentId(), after.getName(), after.getAclModuleId()) > 0) {

                    throw new PermException(CODE_RESOURCE_CONFLICT, "同一层级下存在相同名称的权限模块");
                } else if(after.getAclModuleId() != null && after.getAclModuleId().equals(parent.getAclModuleId())) {

                    throw new PermException(CODE_RESOURCE_CONFLICT, "不能将自己修改到自己下面");
                }
            }
        }

        return null == parent ?
                LevelUtil.calculateLevel(null, null)
                :
                LevelUtil.calculateLevel(parent.getLevel(), parent.getAclModuleId());
    }

    /**
     * 更新父SysAclModule时可能需要更新子SysAclModule
     * @param before
     * @param after
     */
    private void updateAclModuleWithChild(SysAclModule before, SysAclModule after) {
        List<SysAclModule> update = new LinkedList<>();
        update.add(after);

        if (!before.getLevel().equals(after.getLevel())) {

            String level = before.getLevel();
            if (level.equals(LevelUtil.ROOT)) {
                level = LevelUtil.calculateLevel(before.getLevel(), before.getAclModuleId());
            } else {
                level = before.getLevel() + LevelUtil.SEPARATOR;
            }

            List<SysAclModule> children = sysAclModuleMapper.getChildrenDeptByLevel(level);
            if (!CollectionUtils.isEmpty(children)) {
                for (SysAclModule ch : children) {
                    if (ch.getLevel().indexOf(before.getLevel()) == 0) {
                        String newLevel = after.getLevel() + ch.getLevel().substring(before.getLevel().length());

                        SysAclModule chAfter = SysAclModule.builder()
                                        .level(newLevel)
                                        .level(newLevel)
                                        .operator(after.getOperator())
                                        .operateIp(after.getOperateIp())
                                        .aclModuleId(ch.getAclModuleId())
                                        .operateTime(after.getOperateTime())
                                        .build();

                        update.add(chAfter);
                    }
                }
            }
        }

        sysAclModuleMapper.batchUpdateByPrimaryKeySelective(update);
    }

}
