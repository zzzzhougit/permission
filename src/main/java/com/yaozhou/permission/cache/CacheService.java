package com.yaozhou.permission.cache;

/**
 *
 * @author Yao.Zhou
 * @since 2018/7/28 16:37
 */
public interface CacheService<T> {

    /**
     * 获得一条缓存记录
     * @param keyPrefix
     * @return
     */
    public T get(KeyPrefix keyPrefix, String key);

    /**
     * 设置一条缓存记录
     * @param keyPrefix
     * @param value
     */
    public void set(KeyPrefix keyPrefix, String key, T value);

    /**
     * 移除一条缓存记录
     * @param keyPrefix
     */
    public void remove(KeyPrefix keyPrefix, String key);

}