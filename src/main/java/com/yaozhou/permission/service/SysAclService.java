package com.yaozhou.permission.service;

import com.yaozhou.permission.model.PageResult;
import com.yaozhou.permission.model.SysAcl;
import com.yaozhou.permission.model.SysUser;
import com.yaozhou.permission.params.AclParam;
import com.yaozhou.permission.params.PageParam;
import com.yaozhou.permission.status.StatusCode;

/**
 * @author Yao.Zhou
 * @since 2018/7/31 23:31
 */
public interface SysAclService extends StatusCode {

    /**
     * 添加一个Acl
     * @param aclParam
     * @param operator
     * @param ipaddr
     */
    public void add(AclParam aclParam, SysUser operator, String ipaddr);

    /**
     * 删除一个acl
     * @param aclId
     */
    public void delete(Integer aclId);

    /**
     * 更新Acl
     * 如果Acl在缓存中, 同时更新缓存
     *
     * @param aclParam
     * @param operator
     * @param ipaddr
     */
    public void update(AclParam aclParam, SysUser operator, String ipaddr);

    /**
     * 根据aclModuleId分页获取acl
     * @param aclModuleId
     * @param pageParam
     * @return
     */
    public PageResult<SysAcl> getPageByAclModuleId(int aclModuleId, PageParam pageParam);

}
