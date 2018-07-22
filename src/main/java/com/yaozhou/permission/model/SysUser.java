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
public class SysUser implements Serializable {

    private static final long serialVersionUID = 5962324950971243561L;

    private Integer userId;

    private String username;

    private String salt;

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