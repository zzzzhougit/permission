package com.yaozhou.permission.cache.keyprefix.impl;

import com.yaozhou.permission.cache.keyprefix.BaseKeyPrefix;

/**
 * @author Yao.Zhou
 * @since 2018/7/28 16:51
 */
public class UserKeyPrefix extends BaseKeyPrefix {

    private static final int EXPIRE_TIME_USERNAME_TO_USER = MINUTE * 30;

    public UserKeyPrefix(String prefix) {
        super(prefix);
    }

    public UserKeyPrefix(String prefix, int expireSeconds) {
        super(expireSeconds, prefix);
    }

    public static final UserKeyPrefix CACHE_KEY_USERNAME_TO_USER = new UserKeyPrefix("uid", EXPIRE_TIME_USERNAME_TO_USER);

}
