package com.yaozhou.permission.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SysRoleAcl implements Serializable {

    private Integer roleAclId;

    private Integer roleId;

    private Integer aclId;

    private Date createTime;

    private Date operateTime;

    private String operator;

    private String operateIp;

    private static final long serialVersionUID = 1L;

}