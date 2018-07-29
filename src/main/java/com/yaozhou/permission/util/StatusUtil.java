package com.yaozhou.permission.util;

import com.yaozhou.permission.model.SysUser;

/**
 * @author Yao.Zhou
 * @since 2018/7/29 20:09
 */
public class StatusUtil {

    /**
     * 判断用户状态是否正常
     * @param sysUser
     * @return
     */
    public static boolean sysUserStatusOk(SysUser sysUser) {
        if (null == sysUser || sysUser.getStatus() != 1) {

            return false;
        }

        return true;
    }

}
