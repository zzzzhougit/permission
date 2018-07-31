package com.yaozhou.permission.cache.service;

import com.yaozhou.permission.cache.CacheService;
import com.yaozhou.permission.cache.KeyPrefix;
import com.yaozhou.permission.common.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单机版的内存缓存
 *
 * @author Yao.Zhou
 * @since 2018/7/28 16:41
 * @see
 */
@Slf4j
@Service
public class MemoryCacheService<T> implements CacheService<T> {

    @Override
    public boolean exist(KeyPrefix keyPrefix, String key) {
        return null == this.get(keyPrefix, key) ? false : true;
    }

    @Override
    public T get(KeyPrefix keyPrefix, String key) {
        long time = System.currentTimeMillis();

        T value = null;
        ValueWithTtl valueWithTtl = cache.get(keyPrefix.getFullKey(key));
        if (null != value) {
            if (valueWithTtl.expired(time)) {
                cache.remove(keyPrefix.getFullKey(key));
            } else {
                value = (T) valueWithTtl.value();
            }
        }

        return value;
    }

    @Override
    public void set(KeyPrefix keyPrefix, String key, T value) {
        cache.put(keyPrefix.getFullKey(key), new ValueWithTtl<>(value, keyPrefix.expireSeconds()));
    }

    @Override
    public boolean setEx(KeyPrefix keyPrefix, String key, T value) {
        ValueWithTtl<T> cachedValue = cache.replace(keyPrefix.getFullKey(key), new ValueWithTtl<>(value, keyPrefix.expireSeconds()));

        return null == cachedValue ? false : true;

    }

    @Override
    public T setNx(KeyPrefix keyPrefix, String key, T value) {
        ValueWithTtl<T> result = cache.putIfAbsent(keyPrefix.getFullKey(key), new ValueWithTtl<>(value, keyPrefix.expireSeconds()));

        return null == result ? null : result.value();
    }

    @Override
    public void remove(KeyPrefix keyPrefix, String key) {
        cache.remove(keyPrefix.getFullKey(key));
    }

    //==============================================================================

    /**
     * 缓存Map
     */
    private final Timer timmer = new Timer();
    private final Map<String, ValueWithTtl<T>> cache = new ConcurrentHashMap<>();

    public MemoryCacheService() {
        timmer.schedule(new TimerTask() {
            @Override
            public void run() {
                cache.keySet().forEach(key -> {
                    ValueWithTtl<T> valueWithTtl = cache.get(key);
                    if (valueWithTtl.expired(System.currentTimeMillis())) {
                        cache.remove(key);

                        if (log.isDebugEnabled()) {
                            log.info("key{}, value{} is expored", key, valueWithTtl.value());
                        }
                    }
                });
            }
        }, 0, TimeUnit.MINUTE * 30 * 1000);
    }

    /**
     * 实际的cache存储值
     * @param <T>
     */
    private static class ValueWithTtl<T> {
        private T value;
        private long ttl;

        public ValueWithTtl(T value, int expireSeconds) {
            this.value = value;
            this.ttl = expireSeconds <= 0 ? 0 : System.currentTimeMillis() + expireSeconds * 1000;
        }

        public boolean expired(long time) {
            if (this.ttl > 0 && ttl < time) {

                return true;
            }

            return false;
        }

        public T value() {
            return this.value;
        }
    }

}
