package com.yaozhou.permission.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class SysUser implements Serializable {

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

    private static final long serialVersionUID = 1L;

}