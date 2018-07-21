package com.yaozhou.permission.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SysLogWithBLOBs extends SysLog implements Serializable {

    private String oldValue;

    private String newValue;

    private static final long serialVersionUID = 1L;

}