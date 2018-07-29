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
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(DeptParam deptParam) throws Exception {
        SysDept before = sysDeptMapper.selectByPrimaryKey(deptParam.getDeptId());
        if (null == before) {
            throw new PermException(CODE_RESOURCE_NOT_EXIST, "更新的部门不存在");
        }

        SysDept after = SysDept.builder()
            .seq(deptParam.getSeq())
            .level(before.getLevel())
            .name(deptParam.getName())
            .deptId(before.getDeptId())
            .remark(deptParam.getRemark())
            .parentId(deptParam.getParentId())

            //TODO
            .operator("System")
            .operateIp("127.0.0.1")
            .operateTime(new Date())
            .build();
        after.setLevel(checkAndCalculateLevel(after));

        updateWithChild(before, after);
    }

    @Override
    public SysDept selectByPrimaryKey(int deptId) {
        return sysDeptMapper.selectByPrimaryKey(deptId);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void add(DeptParam deptParam) throws Exception {
        SysDept sysDept = SysDept.builder()
            .seq(deptParam.getSeq())
            .name(deptParam.getName())
            .remark(deptParam.getRemark())
            .parentId(deptParam.getParentId())

            //TODO
            .operator("System")
            .operateIp("127.0.0.1")
            .build();
        sysDept.setLevel(checkAndCalculateLevel(sysDept));

        sysDeptMapper.insertSelective(sysDept);
    }

    //===================================

    /**
     * 计算Level值
     * @param after  更新之后的Dept
     * @return
     */
    private String checkAndCalculateLevel(SysDept after) throws PermException {
        //如果上级部门不为空, 检查上级部门
        SysDept parent = null;
        if (null != after.getParentId() && after.getParentId() > 0) {
            parent = sysDeptMapper.selectByPrimaryKey(after.getParentId());
            if (null == parent) {

                throw new PermException(CODE_RESOURCE_NOT_EXIST, "上级部门不存在");
            } else {

                if (sysDeptMapper.countByNameAndParentId(after.getParentId(), after.getName(), after.getDeptId()) > 0) {

                    throw new PermException(CODE_RESOURCE_CONFLICT, "同一层级下存在相同名称的部门");
                } else if (after.getDeptId() != null && after.getDeptId().equals(parent.getDeptId())) {

                    throw new PermException(CODE_RESOURCE_CONFLICT, "不能将自己修改到自己下面");
                }
            }
        }

        return null == parent ?
                    LevelUtil.calculateLevel(null, null)
                :
                    LevelUtil.calculateLevel(parent.getLevel(), parent.getDeptId());
    }

    /**
     * 更新父SysDept可能需要同时更新子SysDept
     * @param before
     * @param after
     */
    private void updateWithChild(SysDept before, SysDept after) {
        List<SysDept> update = new LinkedList<>();
        update.add(after);

        //如果更新了level, 需要更新子部门的level
        if (!before.getLevel().equals(after.getLevel())) {

            String level = before.getLevel();
            if (level.equals(LevelUtil.ROOT)) {
                level = LevelUtil.calculateLevel(before.getLevel(), before.getDeptId());
            } else {
                level = before.getLevel() + LevelUtil.SEPARATOR;
            }

            List<SysDept> childrenSysDept = sysDeptMapper.getChildrenDeptByLevel(level);
            if (!CollectionUtils.isEmpty(childrenSysDept)) {

                for (SysDept ch : childrenSysDept) {
                    if (ch.getLevel().indexOf(before.getLevel()) == 0) {
                        String newLevel = after.getLevel() + ch.getLevel().substring(before.getLevel().length());

                        SysDept chAfter = SysDept.builder()
                            .deptId(ch.getDeptId())
                            .level(newLevel)
                            .operateIp(after.getOperateIp())
                            .operator(after.getOperator())
                            .operateTime(after.getOperateTime())
                            .build();

                        update.add(chAfter);
                    } //end if
                }
            }
        } //end outer if

        sysDeptMapper.batchUpdateByPrimaryKeySelective(update);
    }

}
