package com.yaozhou.permission.service.impl;

import com.yaozhou.permission.common.exception.PermException;
import com.yaozhou.permission.common.result.entity.CodeMessage;
import com.yaozhou.permission.dao.SysDeptMapper;
import com.yaozhou.permission.model.SysDept;
import com.yaozhou.permission.params.DeptParam;
import com.yaozhou.permission.service.SysDeptService;
import com.yaozhou.permission.util.LevelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
        if (null == before) {
            throw new PermException(CodeMessage.create(CODE_RESOURCE_NOT_EXIST, "部门不存在"));
        } else if (exist(deptParam.getParentId(), deptParam.getName(), deptParam.getDeptId())) {
            throw new PermException(CodeMessage.create(CODE_RESOURCE_CONFLICT, "同一层级下存在相同名称的部门"));
        }

        SysDept after = SysDept
                            .builder()
                            .deptId(before.getDeptId())
                            .name(before.getName())
                            .parentId(before.getParentId())
                            .seq(before.getSeq())
                            .remark(before.getRemark())
                            //TODO
                            .operator("System")
                            .operateIp("127.0.0.1")
                            .operateTime(new Date())
                            .level(
                                calculateLevel(deptParam.getParentId())
                            )
                            .build();

        updateWithChild(before, after);
    }

    @Override
    public void add(DeptParam deptParam) throws Exception {
        if (exist(deptParam.getParentId(), deptParam.getName(), deptParam.getDeptId())) {
            throw new PermException(CodeMessage.create(CODE_RESOURCE_CONFLICT, "同一层级下存在相同名称的部门"));
        }

        SysDept sysDept = SysDept
                                .builder()
                                .seq(deptParam.getSeq())
                                .name(deptParam.getName())
                                .remark(deptParam.getRemark())
                                .parentId(deptParam.getParentId())
                                //TODO
                                .operator("System")
                                .operateIp("127.0.0.1")
                                .level(
                                    calculateLevel(deptParam.getParentId())
                                )
                                .build();

        sysDeptMapper.insert(sysDept);
    }

    //===================================

    /**
     * 更新父SysDept可能需要同时更新子SysDept
     * @param before
     * @param after
     */
    @Transactional(isolation = Isolation.READ_COMMITTED)
    private void updateWithChild(SysDept before, SysDept after) {
        //如果更新了level, 需要更新子部门的level
        if (!before.getLevel().equals(after)) {
            List<SysDept> childrenSysDept = sysDeptMapper.getChildrenDeptByLevel(before.getLevel());
            if (!CollectionUtils.isEmpty(childrenSysDept)) {
                List<SysDept> childrenSysDeptAfter = new LinkedList<>(); //children to be update

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

                        childrenSysDeptAfter.add(chAfter);
                    } //end if
                }

                //update children
                sysDeptMapper.batchUpdateByPrimaryKeySelective(childrenSysDeptAfter);
            }
        }

        sysDeptMapper.updateByPrimaryKeySelective(after);

    }

    /**
     * 根据Parent计算Level
     * @param parentId
     * @return
     */
    private String calculateLevel(Integer parentId) {
        return LevelUtil.calculateLevel(
                    sysDeptMapper.selectLevelByPrimaryKey(parentId), parentId
                );
    }

    /**
     * 检查部门是否存在
     * @param parentId
     * @param deptName
     * @param deptId
     * @return
     */
    private boolean exist(Integer parentId, String deptName, Integer deptId) {
        return sysDeptMapper.countByNameAndParentId(parentId, deptName, deptId) > 0;
    }

}
