package com.yaozhou.permission.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SysRole implements Serializable {

    private static final long serialVersionUID = 8211216611387272327L;

    private Integer roleId;

    private String name;

    private Short type;

    private Short status;

    private String remark;

    private Date createTime;

    private Date operateTime;

    private String operator;

    private String operateIp;

}