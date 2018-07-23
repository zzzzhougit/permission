package com.yaozhou.permission.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SysLogWithBLOBs extends SysLog implements Serializable {

    private static final long serialVersionUID = 830217319743200531L;

    private String oldValue;

    private String newValue;
}