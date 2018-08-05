package com.yaozhou.permission.controllers.sys;

import com.yaozhou.permission.common.result.Result;
import com.yaozhou.permission.controllers.BaseController;
import com.yaozhou.permission.dto.AclModuleLevelDto;
import com.yaozhou.permission.filters.NeedLogin;
import com.yaozhou.permission.model.SysUser;
import com.yaozhou.permission.params.RoleParam;
import com.yaozhou.permission.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Yao.Zhou
 * @since 2018/8/5 13:04
 */
@Slf4j
@NeedLogin
@Controller
@RequestMapping(path = "/sys/role")
public class SysRoleController extends BaseController {

    /**
     * role视图
     * @return
     */
    @RequestMapping(method = GET)
    public String view() {

        return "sys/role";
    }

    //======================================

    /**
     * 新增角色
     * @param request
     * @param roleParam
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/add", method = {GET, POST})
    public Result<?> add(HttpServletRequest request, RoleParam roleParam) {
        sysRoleService.add(roleParam, authService.getCurrentUser(request), IpUtil.getRemoteIp(request));

        return Result.success(null);
    }

    /**
     * 更新角色
     * @param request
     * @param roleParam
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/update", method = {GET, POST})
    public Result<?> update(HttpServletRequest request, RoleParam roleParam) {
        sysRoleService.update(roleParam, authService.getCurrentUser(request), IpUtil.getRemoteIp(request));

        return Result.success(null);
    }

    /**
     * 删除角色
     * @param request
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/delete", method = {GET, POST})
    public Result<?> delete(HttpServletRequest request, @RequestParam("roleId") Integer roleId) {
        //TODO

        return Result.success(null);
    }

    /**
     * 获取所有的role
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/list", method = {GET, POST})
    public Result<?> list() {

        return Result.success(sysRoleService.getAll());
    }

    /**
     * 角色树
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping(path = "/roletree", method = {GET, POST})
    public Result<?> roleTree(HttpServletRequest request, @RequestParam("roleId")int roleId) throws Exception {
        SysUser sysUser = authService.getCurrentUser(request);
        List<AclModuleLevelDto> roleTree = sysTreeService.roleTree(sysUser.getUserId(), roleId);

        return Result.success(roleTree);
    }

    /**
     * 更新角色的Acl
     * @param request
     * @param roleId
     * @param aclIds
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(path = "/updateRoleAcls", method = {GET, POST})
    public Result<?> updateRoleAcls(HttpServletRequest request,
                                    @RequestParam("roleId")int roleId,
                                    @RequestParam(value = "aclIds", required = false, defaultValue = "")String aclIds) throws Exception {
        List<Integer> aclIdList = null;
        try {
            if (aclIds.trim().length() == 0) {
                aclIdList = new ArrayList<>();
            } else {
                aclIdList = Arrays.asList(aclIds.split(","))
                        .stream()
                        .filter(e -> e.trim().length() > 0)
                        .map(e -> Integer.parseInt(e))
                        .collect(Collectors.toList());
            }
        } catch (Exception e) {

            return Result.error(CODE_ARGE_ERROR, "aclIds格式错误");
        }

        sysRoleAclService.updateRoleAcls(roleId, aclIdList, authService.getCurrentUser(request), IpUtil.getRemoteIp(request));

        return Result.success(null);
    }

    @ResponseBody
    @RequestMapping(path = "/users", method = {GET, POST})
    public Result<?> users(HttpServletRequest request, @RequestParam("roleId")int roleId) throws Exception {
        SysUser sysUser = authService.getCurrentUser(request);
        List<AclModuleLevelDto> roleTree = sysTreeService.roleTree(sysUser.getUserId(), roleId);

        return Result.success(roleTree);
    }

}
