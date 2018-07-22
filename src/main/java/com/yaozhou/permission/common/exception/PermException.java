package com.yaozhou.permission.common.exception;

import com.yaozhou.permission.common.result.entity.CodeMessage;
import com.yaozhou.permission.common.result.entity.PermEntity;

/**
 * 所有的业务异常都继承于此
 * @author Yao.Zhou
 * @since 2018/7/21 18:16
 */
public class PermException extends RuntimeException {

    private static final long serialVersionUID = -7082935564133428475L;

    private PermEntity exceptionEntity;

    public PermException(int code, String message) {
        super(message);
        this.exceptionEntity = new CodeMessage(code, message);
    }

    public PermException(PermEntity exceptionEntity) {
        super(exceptionEntity.getMessage());
        this.exceptionEntity = exceptionEntity;
    }

    public PermEntity getExceptionEntity() {
        return this.exceptionEntity;
    }

}
