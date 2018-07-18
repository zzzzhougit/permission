package com.yaozhou.permission.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SysLogWithBLOBs extends SysLog implements Serializable {
    private String oldValue;

    private String newValue;

    private static final long serialVersionUID = 1L;

}