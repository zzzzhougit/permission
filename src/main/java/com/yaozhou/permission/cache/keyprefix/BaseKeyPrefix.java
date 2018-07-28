package com.yaozhou.permission.cache.keyprefix;

import com.yaozhou.permission.cache.KeyPrefix;

/**
 * @author Yao.Zhou
 * @since 2018/7/28 17:16
 */
public class BaseKeyPrefix implements KeyPrefix {

    private String prefix;
    private int expireSeconds;

    public BaseKeyPrefix(String prefix) {
        this(0, prefix);
    }

    public BaseKeyPrefix(int expireSeconds, String prefix) {
        this.prefix = prefix;
        this.expireSeconds = expireSeconds;
    }

    @Override
    public String getPrefix() {
        return getClass().getSimpleName() + ":" + this.prefix;
    }

    @Override
    public int expireSeconds() {
        return this.expireSeconds;
    }

    @Override
    public String getFullKey(String key) {
        return this.getPrefix() + ":" + key;
    }

}
