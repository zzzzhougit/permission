package com.yaozhou.permission.filters.impl;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 参数解码过滤器
 * @author Yao.Zhou
 * @since 2018/7/28 12:59
 */
public class ParamsDecodeFilter extends OncePerRequestFilter {

    private String doecode(String value) {
        String ret = value;
        if (null == value || value.trim().equals("(null)")) {
            return null;
        }

        try {
            ret = URLDecoder.decode(URLDecoder.decode(value, "utf-8"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return ret;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(new HttpServletRequestWrapper(request) {
            @Override
            public String getParameter(String name) {
                return doecode(super.getParameter(name));
            }

            @Override
            public String[] getParameterValues(String name) {
                String[] values = super.getParameterValues(name);
                if (null == values) {

                    return null;
                }

                for (int i = 0; i < values.length; i++) {
                    values[i] = doecode(values[i]);
                }

                return values;
            }
        }, response);
    }

}
