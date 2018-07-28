package com.yaozhou.permission.controllers.sys;

import com.yaozhou.permission.common.result.Result;
import com.yaozhou.permission.controllers.BaseController;
import com.yaozhou.permission.filters.NeedLogin;
import com.yaozhou.permission.params.DeptParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

/**
 * @author Yao.Zhou
 * @since 2018/7/18 0:34
 */
@Slf4j
@NeedLogin
@Controller
@RequestMapping(path = "/sys/dept")
public class SysDeptController extends BaseController {

    /**
     * 部门管理视图
     * @param model
     * @return
     */
    @RequestMapping(path = "view", method = {GET})
    public Object view(Model model) {
        //TODO

        return "dept/dept.html";
    }

    //====================================================

    /**
     * 增加一个部门
     * @param deptParam
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(path = "/add", method = {GET, POST})
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
    @ResponseBody
    @RequestMapping(path = "/update", method = {GET, POST})
    public Result<?> updateDept(@Valid DeptParam deptParam) throws Exception {
        sysDeptService.update(deptParam);

        return Result.success(null);
    }

    /**
     * 获得部门树
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(path = "/tree", method = {GET, POST})
    public Result<?> tree() throws Exception {

        return Result.success(sysTreeService.deptTree());
    }

}
