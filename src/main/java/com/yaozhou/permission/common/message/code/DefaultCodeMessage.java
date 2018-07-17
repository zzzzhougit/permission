package com.yaozhou.permission.common.message.code;

/**
 * 默认错误消息实现, 其他分支可继承于此
 * @author Yao.Zhou
 * @since 2018/7/17 22:16
 */
public class DefaultCodeMessage implements CodeMessage {

    /**
     *
     */

    private int code;
    private String message;

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
