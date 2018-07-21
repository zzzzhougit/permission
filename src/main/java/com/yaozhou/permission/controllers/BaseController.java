package com.yaozhou.permission.controllers;

import com.yaozhou.permission.service.SysDeptService;
import com.yaozhou.permission.service.SysTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @author Yao.Zhou
 * @since 2018/7/17 23:45
 */
public class BaseController {

    @Autowired
    private SysTreeService sysTreeService;
    @Autowired
    protected SysDeptService sysDeptService;

    @Autowired
    protected ApplicationContext applicationContext;

    public <T> T popBean(Class<T> clazz) {
        if (null == applicationContext) {

            return null;
        }

        return applicationContext.getBean(clazz);
    }

    public <T> T popBean(String name, Class<T> clazz) {
        if (null == applicationContext) {

            return null;
        }

        return applicationContext.getBean(name, clazz);
    }

}
