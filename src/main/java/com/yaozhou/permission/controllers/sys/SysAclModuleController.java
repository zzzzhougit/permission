package com.yaozhou.permission.controllers.sys;

import com.yaozhou.permission.common.result.Result;
import com.yaozhou.permission.controllers.BaseController;
import com.yaozhou.permission.filters.NeedLogin;
import com.yaozhou.permission.params.AclModuleParam;
import com.yaozhou.permission.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Yao.Zhou
 * @since 2018/7/31 18:10
 */
@Slf4j
@NeedLogin
@Controller
@RequestMapping(path = "/sys/aclmodule")
public class SysAclModuleController extends BaseController {

    /**
     * aclmodule视图
     * @return
     */
    @RequestMapping(method = GET)
    public String view() {

        return "sys/acl";
    }

    //=====================================================

    /**
     * 获得权限模块树
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/tree", method = {GET, POST})
    public Result<?> aclModuleTree() throws Exception {

        return Result.success(sysTreeService.aclModuleTree());
    }

    /**
     * 删除权限模块
     * @param request
     * @param aclModuleParam
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/delete", method = {GET, POST})
    public Result<?> deleteAclModule(HttpServletRequest request, @Valid AclModuleParam aclModuleParam) {

        return Result.success(null);
    }

    /**
     * 添加权限模块
     * @param request
     * @param aclModuleParam
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/add", method = {GET, POST})
    public Result<?> saveAclModule(HttpServletRequest request, @Valid AclModuleParam aclModuleParam) {
        sysAclModuleService.add(aclModuleParam, authService.getCurrentUser(request), IpUtil.getRemoteIp(request));

        return Result.success(null);
    }

    /**
     * 更新权限模块
     * @param request
     * @param aclModuleParam
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/update", method = {GET, POST})
    public Result<?> updateAclModule(HttpServletRequest request, @Valid AclModuleParam aclModuleParam) {
        sysAclModuleService.update(aclModuleParam, authService.getCurrentUser(request), IpUtil.getRemoteIp(request));

        return Result.success(null);
    }

}
