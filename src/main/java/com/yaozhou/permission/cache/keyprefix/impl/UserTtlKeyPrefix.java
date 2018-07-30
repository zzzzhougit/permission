package com.yaozhou.permission.cache.keyprefix.impl;

import com.yaozhou.permission.cache.keyprefix.BaseKeyPrefix;

/**
 * @author Yao.Zhou
 * @since 2018/7/30 16:48
 */
public class UserTtlKeyPrefix extends BaseKeyPrefix {

    private static final int EXPIRE_TIME_USER_TTL = MINUTE * 30;

    public UserTtlKeyPrefix(String prefix) {
        super(prefix);
    }

    public UserTtlKeyPrefix(String prefix, int expireSeconds) {
        super(expireSeconds, prefix);
    }

    public static final UserTtlKeyPrefix KEY_PREFIX_USER_TTL = new UserTtlKeyPrefix("ttl", EXPIRE_TIME_USER_TTL);

}
