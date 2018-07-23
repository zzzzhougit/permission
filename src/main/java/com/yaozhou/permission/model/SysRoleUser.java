package com.yaozhou.permission.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SysRoleUser implements Serializable {

    private static final long serialVersionUID = -444286909481954002L;

    private Integer roleUserId;

    private Integer roleId;

    private Integer userId;

    private Date createTime;

    private Date operateTime;

    private String operator;

    private String operateIp;

}