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
public class SysLog implements Serializable {

    private static final long serialVersionUID = -6319010073922166987L;

    private Integer logId;

    private Short type;

    private Integer targetId;

    private Date createTime;

    private Date operateTime;

    private String operator;

    private String operateIp;

    private Short status;

}