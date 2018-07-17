package com.yaozhou.permission.common.message.code;

/**
 * 默认错误消息实现, 其他分支可继承于此
 * @author Yao.Zhou
 * @since 2018/7/17 22:16
 */
public class DefaultCodeMessage implements CodeMessage {

    /**
     * 参数校验异常
     * code: 20000xx
     */
    public static final int CODE_ARGE_ERROR = 2000001;

    /**
     * 服务器异常
     * code: 50000xx
     */
    public static final int CODE_SERVER_ERROR = 5000001;
    public static final CodeMessage SERVER_ERROR = new DefaultCodeMessage(CODE_SERVER_ERROR, "服务器异常");

    //======================================

    protected int code;
    protected String message;

    public DefaultCodeMessage(int code, String message) {
        this.code = code;
        this.message = message;
    }

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
