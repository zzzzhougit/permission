package com.yaozhou.permission.common;

/**
 * 以秒为单位的时间单位
 * @author Yao.Zhou
 * @since 2018/7/28 16:58
 */
public interface TimeUnit {

    public static final int SECOND = 1;

    public static final int MINUTE = SECOND * 60;

    public static final int HOURSE = MINUTE * 60;

    public static final int DAY  = HOURSE * 24;

}
