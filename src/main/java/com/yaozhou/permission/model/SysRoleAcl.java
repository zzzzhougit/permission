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
public class SysRoleAcl implements Serializable {

    private static final long serialVersionUID = -9158314754843537283L;

    private Integer roleAclId;

    private Integer roleId;

    private Integer aclId;

    private Date createTime;

    private Date operateTime;

    private String operator;

    private String operateIp;

}