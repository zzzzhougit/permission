package com.yaozhou.permission.common.result.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Yao.Zhou
 * @since 2018/7/21 16:48
 */
@Getter
@Setter
public class ViewMessage implements PermEntity {

    private int code;
    private String message;
    private String path;

}
