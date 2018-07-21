package com.yaozhou.permission.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SysUser implements Serializable {

    private static final long serialVersionUID = 5962324950971243561L;

    private Integer userId;

    private String username;

    private String password;

    private String telephone;

    private String mail;

    private Integer deptId;

    private Short status;

    private String remark;

    private Date createTime;

    private String operator;

    private Date operateTime;

    private String operateIp;

}