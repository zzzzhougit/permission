package com.yaozhou.permission.cache.service;

import com.yaozhou.permission.cache.KeyPrefix;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * 基于session的缓存, 超时时间统一是30分钟
 * @author Yao.Zhou
 * @since 2018/7/28 16:41
 * @see
 */
@Service
public class SessionCacheService<T> extends AbstractCacheService<T> {

    /**
     * 获得当前请求的session
     * @return
     */
    private HttpSession getSession() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        return requestAttributes.getRequest().getSession();
    }

    @Override
    public T get(KeyPrefix keyPrefix, String key) {
        return (T) getSession().getAttribute(keyPrefix.getFullKey(key));
    }

    @Override
    public void set(KeyPrefix keyPrefix, String key, T value) {
        getSession().setAttribute(keyPrefix.getFullKey(key), value);
    }

    @Override
    public void remove(KeyPrefix keyPrefix, String key) {
        getSession().removeAttribute(keyPrefix.getFullKey(key));
    }

}
