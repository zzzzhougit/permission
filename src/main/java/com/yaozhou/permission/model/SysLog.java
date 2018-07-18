package com.yaozhou.permission.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class SysLog implements Serializable {
    private Integer logId;

    private Short type;

    private Integer targetId;

    private Date createTime;

    private Date operateTime;

    private String operator;

    private String operateIp;

    private Short status;

    private static final long serialVersionUID = 1L;

}