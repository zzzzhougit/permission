package com.yaozhou.permission.controllers.sys;

import com.yaozhou.permission.common.result.Result;
import com.yaozhou.permission.controllers.BaseController;
import com.yaozhou.permission.interceptors.NeedLogin;
import com.yaozhou.permission.params.DeptParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Yao.Zhou
 * @since 2018/7/18 0:34
 */
@Slf4j
@NeedLogin
@RestController
@RequestMapping(path = "/sys/dept")
public class SysDeptController extends BaseController {

    /**
     * 增加一个部门
     * @param deptParam
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<?> addDept(@Valid DeptParam deptParam) throws Exception {
        sysDeptService.add(deptParam);

        return Result.success(null);
    }

    /**
     * 更新部门
     * @param deptParam
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<?> updateDept(@Valid DeptParam deptParam) throws Exception {
        sysDeptService.update(deptParam);

        return Result.success(null);
    }

    /**
     * 获得部门树
     * @return
     * @throws Exception
     */
    @RequestMapping(path = "/tree", method = {RequestMethod.GET, RequestMethod.POST})
    public Result<?> tree() throws Exception {

        return Result.success(sysTreeService.deptTree());
    }

}
