package com.yaozhou.permission.service.impl;

import com.yaozhou.permission.common.exception.PermException;
import com.yaozhou.permission.mapper.SysAclMapper;
import com.yaozhou.permission.model.PageResult;
import com.yaozhou.permission.model.SysAcl;
import com.yaozhou.permission.model.SysUser;
import com.yaozhou.permission.params.AclParam;
import com.yaozhou.permission.params.PageParam;
import com.yaozhou.permission.service.SysAclService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yao.Zhou
 * @since 2018/7/31 23:32
 */
@Service
public class SysAclServiceImpl implements SysAclService {

    @Autowired
    private SysAclMapper sysAclMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void add(AclParam aclParam, SysUser operator, String ipaddr) {
        SysAcl sysAcl = SysAcl.builder()
                .operateIp(ipaddr)
                .seq(aclParam.getSeq())
                .url(aclParam.getUrl())
                .code(generateAclCode())
                .type(aclParam.getType())
                .name(aclParam.getName())
                .remark(aclParam.getRemark())
                .status(aclParam.getStatus())
                .operator(operator.getUsername())
                .aclModuleId(aclParam.getAclModuleId())
                .build();

        sysAclMapper.insertSelective(sysAcl);
    }

    @Override
    public void delete(Integer aclId) {

    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(AclParam aclParam, SysUser operator, String ipaddr) {
        SysAcl after = SysAcl.builder()
                .operateIp(ipaddr)
                .seq(aclParam.getSeq())
                .url(aclParam.getUrl())
                .operateTime(new Date())
                .type(aclParam.getType())
                .name(aclParam.getName())
                .aclId(aclParam.getAclId())
                .remark(aclParam.getRemark())
                .status(aclParam.getStatus())
                .operator(operator.getUsername())
                .aclModuleId(aclParam.getAclModuleId())
                .build();

        SysAcl before = sysAclMapper.selectByPrimaryKey(aclParam.getAclId());
        if (null == before) {
            throw new PermException(CODE_RESOURCE_NOT_EXIST, "更新的权限点不存在");
        }

        if (exist(aclParam.getAclModuleId(), aclParam.getAclId(), aclParam.getName())) {
            throw new PermException(CODE_RESOURCE_CONFLICT, "权限模块下已存在相同的权限点");
        }

        sysAclMapper.updateByPrimaryKeySelective(after);
    }

    @Override
    public PageResult<SysAcl> getPageByAclModuleId(int aclModuleId, PageParam pageParam) {
        int count = sysAclMapper.countByAclModuleId(aclModuleId);
        if (count > 0) {
            List<SysAcl> aclList = sysAclMapper.getPageByAclModuleId(aclModuleId, pageParam);

            return PageResult.<SysAcl>builder()
                    .data(aclList)
                    .total(count)
                    .pageNo(pageParam.getPageNo())
                    .pageSize(pageParam.getPageSize())
                    .build();
        }

        return PageResult.<SysAcl>builder()
                .total(count)
                .data(new LinkedList<>())
                .pageNo(pageParam.getPageNo())
                .pageSize(pageParam.getPageSize())
                .build();
    }

    //==============================================================

    /**
     * 检查aclModule下是否存在相同的acl
     * @param aclModuleId
     * @param aclId
     * @param name
     * @return
     */
    private boolean exist(int aclModuleId, int aclId, String name) {

        return false;
    }

    /**
     * 生成Acl的code
     * @return
     */
    private String generateAclCode() {
        int random = (int) Math.random() * 100;

        return DateFormatUtils.format(new Date(), "yyyyMMddHHmmss") +  "_"+ random;
    }

}
