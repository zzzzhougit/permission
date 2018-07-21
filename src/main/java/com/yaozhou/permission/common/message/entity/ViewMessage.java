package com.yaozhou.permission.common.message.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Yao.Zhou
 * @since 2018/7/21 16:48
 */
@Getter
@Setter
public class ViewMessage implements ExceptionEntity {

    private int code;
    private String message;
    private String path;

}
