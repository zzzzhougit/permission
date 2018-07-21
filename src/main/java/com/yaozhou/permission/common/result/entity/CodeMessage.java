package com.yaozhou.permission.common.result.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 返回数据类型的异常消息
 * @author Yao.Zhou
 * @since 2018/7/17 22:16
 */
@Getter
@Setter
public class CodeMessage implements PermEntity {

    protected int code;
    protected String message;

    public CodeMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

    //======================================

    public static PermEntity create(int code, String message) {
        return new CodeMessage(code, message);
    }

    public static final CodeMessage SERVER_ERROR = new CodeMessage(CODE_SERVER_ERROR, "服务器异常");


    public static final CodeMessage RESOURCE_CONFLICT = new CodeMessage(CODE_RESOURCE_CONFLICT, "资源冲突");

}
