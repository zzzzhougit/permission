package com.yaozhou.permission.status;

/**
 * @author Yao.Zhou
 * @since 2018/7/22 18:30
 */
public interface StatusCode {

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

}
