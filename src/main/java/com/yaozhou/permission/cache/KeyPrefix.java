package com.yaozhou.permission.cache;

import com.yaozhou.permission.common.TimeUnit;

/**
 * @author Yao.Zhou
 * @since 2018/7/28 16:48
 */
public interface KeyPrefix extends TimeUnit {

    public String getPrefix();

    public int expireSeconds();

    public String getFullKey(String key);

}
