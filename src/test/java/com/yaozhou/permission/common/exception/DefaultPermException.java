package com.yaozhou.permission.common.exception;

import com.yaozhou.permission.common.message.entity.CodeMessage;

/**
 * @author Yao.Zhou
 * @since 2018/7/17 22:39
 */
public class DefaultPermException extends PermException {

    private static final long serialVersionUID = -7082935564133428475L;

    private CodeMessage codeMessage;

    private DefaultPermException(String message) {
        super(message);
    }

    public DefaultPermException(CodeMessage codeMessage) {
        this(codeMessage.getMessage());
        this.codeMessage = codeMessage;
    }

    @Override
    public CodeMessage getCodeMessage() {
        return this.codeMessage;
    }
}
