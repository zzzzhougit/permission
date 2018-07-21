package com.yaozhou.permission.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SysAcl implements Serializable {

    private static final long serialVersionUID = -5400375896175706698L;

    private Integer aclId;

    private String code;

    private String name;

    private Integer aclModuleId;

    private String url;

    private Short type;

    private Short status;

    private Integer seq;

    private String remark;

    private Date createTime;

    private Date operateTime;

    private String operator;

    private String operateIp;

}