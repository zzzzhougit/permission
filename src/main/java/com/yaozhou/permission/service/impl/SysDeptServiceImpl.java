package com.yaozhou.permission.service.impl;

import com.yaozhou.permission.common.exception.PermException;
import com.yaozhou.permission.common.result.entity.CodeMessage;
import com.yaozhou.permission.mapper.SysDeptMapper;
import com.yaozhou.permission.model.SysDept;
import com.yaozhou.permission.params.DeptParam;
import com.yaozhou.permission.service.SysDeptService;
import com.yaozhou.permission.util.LevelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static com.yaozhou.permission.common.result.entity.PermEntity.*;

/**
 * @author Yao.Zhou
 * @since 2018/7/21 15:48
 */
@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public void update(DeptParam deptParam) throws Exception {
        SysDept before = sysDeptMapper.selectByPrimaryKey(deptParam.getDeptId());

        SysDept after = SysDept
                            .builder()
                            .name(deptParam.getName())
                            .parentId(deptParam.getParentId())
                            .seq(deptParam.getSeq())
                            .remark(deptParam.getRemark())
                            .deptId(before.getDeptId())

                            //TODO
                            .operator("System")
                            .operateIp("127.0.0.1")
                            .operateTime(new Date())
                            .build();
        after.setLevel(calculateLevel(before, after));

        updateWithChild(before, after);
    }

    @Override
    public void add(DeptParam deptParam) throws Exception {
        SysDept sysDept = SysDept
                                .builder()
                                .seq(deptParam.getSeq())
                                .name(deptParam.getName())
                                .remark(deptParam.getRemark())
                                .parentId(deptParam.getParentId())
                                //TODO
                                .operator("System")
                                .operateIp("127.0.0.1")
                                .build();
        sysDept.setLevel(calculateLevel(null, sysDept));

        sysDeptMapper.insertSelective(sysDept);
    }

    //===================================

    /**
     * 计算Level值
     * @param before 更新之前的Dept, 如果是新加, before = null
     * @param after  更新之后的Dept
     * @return
     */
    private String calculateLevel(SysDept before, SysDept after) {
        String level = null;
        Integer afterParentId = after.getParentId();

        //查询上级部门
        SysDept parentDept = null;
        if (null != afterParentId && afterParentId > 0) {
            parentDept = sysDeptMapper.selectByPrimaryKey(afterParentId);
            if (null == parentDept) {

                throw new PermException(CodeMessage.create(CODE_RESOURCE_NOT_EXIST, "上级部门不存在"));
            } else {

                if (sysDeptMapper.countByNameAndParentId(afterParentId, after.getName(), after.getDeptId()) > 0) {

                    throw new PermException(CodeMessage.create(CODE_RESOURCE_CONFLICT, "同一层级下存在相同名称的部门"));
                }
            }
        }

        //新增dept
        if (null == before) {
            if (null != afterParentId && afterParentId > 0) {

                level = LevelUtil.calculateLevel(parentDept.getLevel(), afterParentId);
            } else {

                level = LevelUtil.ROOT;
            }

        //更新
        } else {
            //after的parenntId为空或者和before一致, 表示不更新parent
            if (null == afterParentId || afterParentId.equals(before.getParentId())) {
                level =  before.getLevel();

            //重新计算level
            } else {
                level = LevelUtil.calculateLevel(parentDept.getLevel(), afterParentId);
            }

        }

        return level;
    }

    /**
     * 更新父SysDept可能需要同时更新子SysDept
     * @param before
     * @param after
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    private void updateWithChild(SysDept before, SysDept after) {
        List<SysDept> sysDeptAfter = new LinkedList<>();

        //如果更新了level, 需要更新子部门的level
        if (!before.getLevel().equals(after.getLevel())) {
            List<SysDept> childrenSysDept = sysDeptMapper.getChildrenDeptByLevel(before.getLevel());
            if (!CollectionUtils.isEmpty(childrenSysDept)) {

                for (SysDept ch : childrenSysDept) {
                    if (ch.getLevel().indexOf(before.getLevel()) == 0) {
                        String newLevel = after.getLevel() + ch.getLevel().substring(before.getLevel().length());

                        SysDept chAfter = SysDept
                                            .builder()
                                            .deptId(ch.getDeptId())
                                            .level(newLevel)
                                            .operateIp(after.getOperateIp())
                                            .operator(after.getOperator())
                                            .operateTime(after.getOperateTime())
                                            .build();

                        sysDeptAfter.add(chAfter);
                    } //end if
                }
            }
        } //end outer if

        if (sysDeptAfter.size() > 0) {
            sysDeptAfter.add(after);
        }
        sysDeptMapper.batchUpdateByPrimaryKeySelective(sysDeptAfter);
    }

}
