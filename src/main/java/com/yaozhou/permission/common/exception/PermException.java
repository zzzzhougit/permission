package com.yaozhou.permission.common.exception;

import com.yaozhou.permission.common.message.entity.ExceptionEntity;

/**
 * @author Yao.Zhou
 * @since 2018/7/21 18:16
 */
public class PermException extends RuntimeException {

    private static final long serialVersionUID = -7082935564133428475L;

    private ExceptionEntity exceptionEntity;

    public PermException(ExceptionEntity exceptionEntity) {
        super(exceptionEntity.getMessage());
        this.exceptionEntity = exceptionEntity;
    }

    public ExceptionEntity getExceptionEntity() {
        return this.exceptionEntity;
    }

}
