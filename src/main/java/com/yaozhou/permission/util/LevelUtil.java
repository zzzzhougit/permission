package com.yaozhou.permission.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Yao.Zhou
 * @since 2018/7/21 18:25
 */
public class LevelUtil {

    public final static String ROOT = "0";
    public final static String SEPARATOR = ".";

    /**
     * 构造部门的Level值
     * @param parentLevel
     * @param parentId
     * @return
     */
    public static String calculateLevel(String parentLevel, int parentId) {
        if (StringUtils.isBlank(parentLevel) || parentId <= 0) {

            return ROOT;
        } else {

            return StringUtils.join(parentLevel, SEPARATOR, parentId);
        }

    }

}
