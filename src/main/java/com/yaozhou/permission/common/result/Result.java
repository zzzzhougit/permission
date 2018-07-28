package com.yaozhou.permission.common.result;

import com.yaozhou.permission.common.result.entity.PermEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 接口返回消息
 *
 * @author Yao.Zhou
 * @since 2018/7/17 22:06
 */
@Getter
@Setter
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

    public static <T> Result<T> error(PermEntity exceptionEntity) {
        return new Result<T>(false, exceptionEntity.getMessage(), exceptionEntity.getCode(), null);
    }

    public static <T> Result<T> error(int code, String message) {
        return new Result<T>(false, message, code, null);
    }

}
