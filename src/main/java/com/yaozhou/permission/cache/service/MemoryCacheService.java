package com.yaozhou.permission.cache.service;

import com.yaozhou.permission.cache.KeyPrefix;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单机版的内存缓存
 *
 * @author Yao.Zhou
 * @since 2018/7/28 16:41
 * @see
 */
@Service
public class MemoryCacheService<T> extends AbstractCacheService<T> {

    private static final Map<String, ValueWithTtl> cache = new ConcurrentHashMap<>();

    /**
     * cache 的实际存储值
     *
     * @param <T>
     */
    private static class ValueWithTtl<T> {
        private T value;
        private long expiredTime;

        public ValueWithTtl(T value, int ttl) {
            this.value = value;
            this.expiredTime = System.currentTimeMillis() + ttl * 1000;
        }

        public T val() {
            return this.value;
        }

        public boolean expired() {
            if (this.expiredTime > 0 && System.currentTimeMillis() > this.expiredTime) {

                return true;
            }

            return false;
        }
    }

    @Override
    public boolean exist(KeyPrefix keyPrefix, String key) {
        return cache.containsKey(keyPrefix.getFullKey(key));
    }

    @Override
    public T get(KeyPrefix keyPrefix, String key) {
        return (T) cache.get(keyPrefix.getFullKey(key));
    }

    @Override
    public void set(KeyPrefix keyPrefix, String key, T value) {
        ValueWithTtl<T> valueWithTtl = new ValueWithTtl<>(value, keyPrefix.expireSeconds());
        cache.put(keyPrefix.getFullKey(key), valueWithTtl);
    }

    /**
     * Not Atomic
     * @param keyPrefix
     * @param key
     * @param value
     */
    @Deprecated
    @Override
    public boolean setEx(KeyPrefix keyPrefix, String key, T value) {
        if (null != cache.get(keyPrefix.getFullKey(key))) {
            ValueWithTtl<T> valueWithTtl = new ValueWithTtl<>(value, keyPrefix.expireSeconds());
            cache.put(keyPrefix.getFullKey(key), valueWithTtl);

            return true;
        } else {
            return false;
        }
    }

    @Override
    public T setNx(KeyPrefix keyPrefix, String key, T value) {
        ValueWithTtl<T> valueWithTtl = new ValueWithTtl<>(value, keyPrefix.expireSeconds());
        return (T) cache.putIfAbsent(keyPrefix.getFullKey(key), valueWithTtl);
    }

    @Override
    public void remove(KeyPrefix keyPrefix, String key) {
        cache.remove(keyPrefix.getFullKey(key));
    }

}
