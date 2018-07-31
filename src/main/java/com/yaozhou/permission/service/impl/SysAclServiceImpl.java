package com.yaozhou.permission.service.impl;

import com.yaozhou.permission.mapper.SysAclMapper;
import com.yaozhou.permission.model.PageResult;
import com.yaozhou.permission.model.SysAcl;
import com.yaozhou.permission.model.SysUser;
import com.yaozhou.permission.params.AclParam;
import com.yaozhou.permission.params.PageParam;
import com.yaozhou.permission.service.SysAclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void add(AclParam aclParam, SysUser operator, String ipaddr) {

    }

    @Override
    public void update(AclParam aclParam, SysUser operator, String ipaddr) {

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

}
