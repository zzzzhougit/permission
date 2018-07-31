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
     *
     * @param keyPrefix
     */
    public void remove(KeyPrefix keyPrefix, String key);

    /**
     * 如果Key存在, 更新值
     *
     * @param keyPrefix
     * @param key
     * @param value
     * @return 如果更新成功返回true
     */
    public boolean setEx(KeyPrefix keyPrefix, String key, T value);

    /**
     * 如果key不存在, 设置值
     *
     * @param keyPrefix
     * @param key
     * @param value
     * @return 如果key不存在, 返回null并更新K-V; 如果key存在, 返回key旧的值
     */
    public T setNx(KeyPrefix keyPrefix, String key, T value);

    /**
     * 判断key是否存在
     *
     * @param keyPrefix
     * @param key
     * @return
     */
    public boolean exist(KeyPrefix keyPrefix, String key);

}
