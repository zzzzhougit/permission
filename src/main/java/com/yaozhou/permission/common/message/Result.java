package com.yaozhou.permission.common.message;

import com.yaozhou.permission.common.message.entity.CodeMessage;

/**
 * 接口返回消息
 *
 * @author Yao.Zhou
 * @since 2018/7/17 22:06
 */
public class Result<T> {

    private T data;
    private int code;
    private String message;
    private boolean success;

    private Result(boolean success, String message, int code, T data) {
        this.code = code;
        this.data = data;
        this.success = success;
        this.message = message;
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(true, "", 0, data);
    }

    public static <T> Result<T> error(CodeMessage codeMessage) {
        return new Result<T>(false, codeMessage.getMessage(), codeMessage.getCode(), null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
