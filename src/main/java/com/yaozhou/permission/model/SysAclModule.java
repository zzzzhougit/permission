package com.yaozhou.permission.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SysAclModule implements Serializable {

    private static final long serialVersionUID = -81089914522267350L;

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

}