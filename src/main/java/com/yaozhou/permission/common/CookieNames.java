package com.yaozhou.permission.common;

/**
 * cookie名称
 *
 * @author Yao.Zhou
 * @since 2018/7/28 20:31
 */
public interface CookieNames {

    /**
     * 用户类cookie名
     */
    public interface User {

        /**
         * 用户关联信息
         */
        public static final String COOKIE_NAME_USER_INFO = "uf";

        /**
         * 用户关联信息签证
         */
        public static final String COOKIE_NAME_USER_INFO_VISA = "visa";

    }

}
