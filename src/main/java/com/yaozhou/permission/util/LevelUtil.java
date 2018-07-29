package com.yaozhou.permission.util;

import com.yaozhou.permission.model.SysDept;
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
     * @param parentDeptId
     * @return
     */
    public static String calculateLevel(String parentLevel, Integer parentDeptId) {
        String level;

        if (null == parentLevel) {
            level = ROOT;
        } else {
            level = StringUtils.join(parentLevel, SEPARATOR, parentDeptId);
        }

        return level;
    }

}
