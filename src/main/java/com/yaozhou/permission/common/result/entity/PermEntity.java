package com.yaozhou.permission.common.result.entity;

/**
 * 错误消息和错误码
 * @author Yao.Zhou
 * @since 2018/7/17 22:14
 */
public interface PermEntity {

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

    /**
     * 资源冲突异常
     * code: 40000xx
     */
    //资源冲突
    public static final int CODE_RESOURCE_CONFLICT = 4000001;
    //资源不存在
    public static final int CODE_RESOURCE_NOT_EXIST = 4000002;

    //====================================================================

    public int getCode();

    public String getMessage();

    public void setMessage(String message);

}
