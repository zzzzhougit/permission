package com.yaozhou.permission.configure;

import org.springframework.util.PathMatcher;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 静态资源匹配器
 * @author Yao.Zhou
 * @since 2018/7/22 16:25
 */
public class StaticPathMatcher implements PathMatcher {

    private static final List<String> staticResourceSuffix = Arrays.asList(
         ".css"
        ,".js"
        ,".html"
    );

    @Override
    public boolean isPattern(String path) {
        return false;
    }

    @Override
    public boolean match(String pattern, String path) {
        for (String suffix : staticResourceSuffix) {
            if (path.endsWith(suffix)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean matchStart(String pattern, String path) {
        return false;
    }

    @Override
    public String extractPathWithinPattern(String pattern, String path) {
        return null;
    }

    @Override
    public Map<String, String> extractUriTemplateVariables(String pattern, String path) {
        return null;
    }

    @Override
    public Comparator<String> getPatternComparator(String path) {
        return null;
    }

    @Override
    public String combine(String pattern1, String pattern2) {
        return null;
    }

}
