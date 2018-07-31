package com.yaozhou.permission.controllers.sys;

import com.yaozhou.permission.common.result.Result;
import com.yaozhou.permission.controllers.BaseController;
import com.yaozhou.permission.filters.NeedLogin;
import com.yaozhou.permission.params.AclParam;
import com.yaozhou.permission.params.PageParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

        return Result.success(aclService.getPageByAclModuleId(aclModuleId, pageParam));
    }

    /**
     * 新加一个acl
     * @param aclParam
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/add", method = {GET, POST})
    public Result<?> addAcl(@Valid AclParam aclParam) {

        return Result.success(null);
    }

    /**
     * 删除一个acl
     * @param aclId
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/delete", method = {GET, POST})
    public Result<?> deleteAcl(@RequestParam("aclId") Integer aclId) {
        if (null == aclId) {

            return Result.error(CODE_ARGE_ERROR, "aclId不能为空");
        }

        return Result.success(null);
    }

    /**
     * 更新一个acl
     * @param aclParam
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/update", method = {GET, POST})
    public Result<?> updateAcl(@Valid AclParam aclParam) {

        return Result.success(null);
    }

}
