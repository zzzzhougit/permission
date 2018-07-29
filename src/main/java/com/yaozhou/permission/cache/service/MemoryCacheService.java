package com.yaozhou.permission.cache.service;

import com.yaozhou.permission.cache.KeyPrefix;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单机版的内存缓存
 * @author Yao.Zhou
 * @since 2018/7/28 16:41
 * @see
 */
@Service
public class MemoryCacheService<T> extends AbstractCacheService<T> {

    private static final Map<String, Object> cache = new ConcurrentHashMap<>();

    @Override
    public T get(KeyPrefix keyPrefix, String key) {
        return (T) cache.get(keyPrefix.getFullKey(key));
    }

    @Override
    public void set(KeyPrefix keyPrefix, String key, T value) {
        cache.put(keyPrefix.getFullKey(key), value);
    }

    /**
     * Not Atomic
     * @param keyPrefix
     * @param key
     * @param value
     */
    @Override
    public void setEx(KeyPrefix keyPrefix, String key, T value) {
        if (null != cache.get(keyPrefix.getFullKey(key))) {
            cache.put(keyPrefix.getFullKey(key), value);
        }
    }

    @Override
    public void remove(KeyPrefix keyPrefix, String key) {
        cache.remove(keyPrefix.getFullKey(key));
    }

}
