package com.yaozhou.permission.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yao.Zhou
 * @since 2018/7/28 17:28
 */
public class CookieUtil {

    /**
     * 获取cookie的value
     * @param key
     * @param request
     * @return
     */
    public static String getCookieValue(String key, HttpServletRequest request) {
        String result = null;
        Map<String, String> cookiesMap = null;

        if (null != key && key.trim().length() > 0) {
            key = key.trim();

            cookiesMap = (Map<String, String>) request.getAttribute("cookiesMap");
            if (null == cookiesMap) {
                cookiesMap = new HashMap<>();

                Cookie[] cookies = request.getCookies();
                if (null != cookies) {
                    for (Cookie cookie : cookies) {
                        String name = cookie.getName();
                        String value = cookie.getValue();

                        if (null != name && null != value) {
                            cookiesMap.put(name, value);
                        }
                    }

                    request.setAttribute("cookiesMap", cookiesMap);
                }
            }

            result = cookiesMap.get(key);
        }

        return result;
    }

    /**
     * 删除一个cookie
     * @param response
     * @param key
     * @param path
     */
    public static void deleteCookie(HttpServletResponse response, String key,String path) {
        Cookie cookie = new Cookie(key, null);
        cookie.setMaxAge(0); cookie.setPath(path);
        response.addCookie(cookie);
    }

    /**
     * 添加一个cookie
     * @param response
     * @param key
     * @param value
     * @param path
     */
    public static void setCookie(HttpServletResponse response, String key, String value, String path, int maxAge) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(maxAge); cookie.setPath(path);
        response.addCookie(cookie);
    }

}
