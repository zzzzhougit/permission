package com.yaozhou.permission.model;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysDept implements Serializable {

    private static final long serialVersionUID = 3727250098121955443L;

    private Integer deptId;

    private String name;

    private Integer parentId;

    private String level;

    private Integer seq;

    private String remark;

    private Date createTime;

    private Date operateTime;

    private String operator;

    private String operateIp;

}