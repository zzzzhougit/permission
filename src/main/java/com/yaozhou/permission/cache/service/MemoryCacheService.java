package com.yaozhou.permission.cache.service;

import com.yaozhou.permission.cache.CacheService;
import com.yaozhou.permission.cache.KeyPrefix;
import org.springframework.stereotype.Service;
import sun.misc.Unsafe;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 单机版的内存缓存
 *
 * @author Yao.Zhou
 * @since 2018/7/28 16:41
 * @see
 */
@Service
public class MemoryCacheService<T> implements CacheService<T> {

    @Override
    public boolean exist(KeyPrefix keyPrefix, String key) {
        return null == getCacheValue(keyPrefix.getFullKey(key)) ? false : true;
    }

    @Override
    public T get(KeyPrefix keyPrefix, String key) {
        return getCacheValue(keyPrefix.getFullKey(key));
    }

    @Override
    public void set(KeyPrefix keyPrefix, String key, T value) {
        setIntoCache(keyPrefix.getFullKey(key), value, keyPrefix.expireSeconds());
    }

    @Override
    public boolean setEx(KeyPrefix keyPrefix, String key, T value) {
        return setIntoCacheIfExist(keyPrefix.getFullKey(key), value, keyPrefix.expireSeconds());
    }

    @Override
    public T setNx(KeyPrefix keyPrefix, String key, T value) {
        return setIntoCacheIfNotExist(keyPrefix.getFullKey(key), value, keyPrefix.expireSeconds());
    }

    @Override
    public void remove(KeyPrefix keyPrefix, String key) {
        removeCacheValue(keyPrefix.getFullKey(key));
    }

    //==============================================================================

    /**
     * 缓存Map
     */
    private final Map<String, ValueWithTtl<T>> cache = new HashMap<>();
    private final Map<String, Object> keysMonitor = new ConcurrentHashMap<>();
    private static final Unsafe UNSAFE = Unsafe.getUnsafe();

    /**
     * 添加值
     * @param key
     * @param value
     * @param expireSeconds
     */
    private void setIntoCache(String key, T value, int expireSeconds) {
        ValueWithTtl<T> valueWithTtl = new ValueWithTtl<>(value, expireSeconds);

        Object monitor = keysMonitor.get(key);
        if (null != monitor) {
            synchronized (monitor) {
                cache.put(key, valueWithTtl);
            }
        } else {
            //keysMonitor.put()
        }

        //Object monitor = new Object();
        keysMonitor.put(key, monitor);
        synchronized (monitor) {
            cache.put(key, valueWithTtl);
        }
    }

    /**
     * 移除缓存的K-V
     * @param key
     */
    private void removeCacheValue(String key) {
        Object monitor = keysMonitor.remove(key);
        if (null != monitor) {
            try {
                synchronized (monitor) {
                    cache.remove(key);
                }
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    /**
     * 根据key获得Value
     * @param key
     * @return
     */
    private T getCacheValue(String key) {
        long time = System.currentTimeMillis();

        T value = null;
        Object monitor = keysMonitor.get(key);
        if (null != monitor) {
            try {
                synchronized (monitor) {
                    ValueWithTtl valueWithTtl = cache.get(key);
                    if (valueWithTtl.expired(time)) {
                        cache.remove(key);
                        keysMonitor.remove(key);
                    } else {
                        value = (T) valueWithTtl.value();
                    }
                }
            } catch (Exception e) { e.printStackTrace(); }
        }

        return value;
    }

    /**
     * 如果缓存不存在K, 更新返回value, 否则返回cacheValue
     * @param key
     * @param value
     * @param expireSeconds
     * @return
     */
    public T setIntoCacheIfNotExist(String key, T value, int expireSeconds) {
        T resultValue = value;

        Object monitor = keysMonitor.get(key);
        if (null != monitor) {
            try {
                synchronized (monitor) {
                    resultValue = getCacheValue(key);
                }
            } catch (Exception e) { e.printStackTrace(); }
        } else {
            setIntoCache(key, value, expireSeconds);
        }

        return resultValue;
    }

    /**
     * 如果缓存存在K, 更新返回true, 否则返回false
     * @param key
     * @param value
     * @param expireSeconds
     * @return
     */
    private boolean setIntoCacheIfExist(String key, T value, int expireSeconds) {
        boolean exist = false;

        Object monitor = keysMonitor.get(key);
        if (null != monitor) {
            try {
                synchronized (monitor) {
                    setIntoCache(key, value, expireSeconds);
                    exist = true;
                }
            } catch (Exception e) { e.printStackTrace(); }
        }

        return exist;
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
