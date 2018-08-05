package com.yaozhou.permission.controllers.sys;

import com.yaozhou.permission.common.result.Result;
import com.yaozhou.permission.controllers.BaseController;
import com.yaozhou.permission.filters.NeedLogin;
import com.yaozhou.permission.params.AclParam;
import com.yaozhou.permission.params.PageParam;
import com.yaozhou.permission.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Yao.Zhou
 * @since 2018/7/31 18:10
 */
@Slf4j
@NeedLogin
@Controller
@RequestMapping(path = "/sys/acl")
public class SysAclController extends BaseController {

    /**
     * 分页获取aclModule下的acl
     * @param aclModuleId
     * @param pageParam
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/listbymodule", method = {GET, POST})
    public Result<?> listByAclModule(@RequestParam("aclModuleId") Integer aclModuleId, @Valid PageParam pageParam) {
        if (null == aclModuleId) {

            return Result.error(CODE_ARGE_ERROR, "aclModuleId不能为空");
        }

        return Result.success(sysAclService.getPageByAclModuleId(aclModuleId, pageParam));
    }

    /**
     * 新加一个acl
     * @param request
     * @param aclParam
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/add", method = {GET, POST})
    public Result<?> addAcl(HttpServletRequest request, @Valid AclParam aclParam) {
        sysAclService.add(aclParam, authService.getCurrentUser(request), IpUtil.getRemoteIp(request));

        return Result.success(null);
    }

    /**
     * 删除一个acl
     * @param request
     * @param aclId
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/delete", method = {GET, POST})
    public Result<?> deleteAcl(HttpServletRequest request, @RequestParam("aclId") Integer aclId) {
        if (null == aclId) {

            return Result.error(CODE_ARGE_ERROR, "aclId不能为空");
        }

        //TODO

        return Result.success(null);
    }

    /**
     * 更新一个acl
     * @param request
     * @param aclParam
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/update", method = {GET, POST})
    public Result<?> updateAcl(HttpServletRequest request, @Valid AclParam aclParam) {
        sysAclService.update(aclParam, authService.getCurrentUser(request), IpUtil.getRemoteIp(request));

        return Result.success(null);
    }

}
