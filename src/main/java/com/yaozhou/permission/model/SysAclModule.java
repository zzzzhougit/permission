package com.yaozhou.permission.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SysAclModule implements Serializable {
    private Integer aclModuleId;

    private String name;

    private Integer parentId;

    private String level;

    private Integer seq;

    private Short status;

    private String remark;

    private Date createTime;

    private Date operateTime;

    private String operator;

    private String operateIp;

    private static final long serialVersionUID = 1L;

}