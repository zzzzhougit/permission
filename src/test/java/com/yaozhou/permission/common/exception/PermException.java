package com.yaozhou.permission.common.exception;

import com.yaozhou.permission.common.message.entity.CodeMessage;

/**
 * @author Yao.Zhou
 * @since 2018/7/17 22:36
 */
public abstract class PermException extends RuntimeException {

    private static final long serialVersionUID = -6527002578622059223L;

    public PermException(String message) {
        super(message);
    }

    public abstract CodeMessage getCodeMessage();

}
