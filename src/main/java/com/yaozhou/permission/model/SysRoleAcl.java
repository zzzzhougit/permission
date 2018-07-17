package com.yaozhou.permission.model;

import java.io.Serializable;
import java.util.Date;

public class SysRoleAcl implements Serializable {
    private Integer roleAclId;

    private Integer roleId;

    private Integer aclId;

    private Date createTime;

    private Date operateTime;

    private String operator;

    private String operateIp;

    private static final long serialVersionUID = 1L;

    public Integer getRoleAclId() {
        return roleAclId;
    }

    public void setRoleAclId(Integer roleAclId) {
        this.roleAclId = roleAclId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAclId() {
        return aclId;
    }

    public void setAclId(Integer aclId) {
        this.aclId = aclId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getOperateIp() {
        return operateIp;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp == null ? null : operateIp.trim();
    }
}