package com.yaozhou.permission.common.result.entity;

import com.yaozhou.permission.status.StatusCode;

/**
 * 错误消息和错误码
 * @author Yao.Zhou
 * @since 2018/7/17 22:14
 */
public interface PermEntity extends StatusCode {

    public int getCode();

    public String getMessage();

    public void setMessage(String message);

}
