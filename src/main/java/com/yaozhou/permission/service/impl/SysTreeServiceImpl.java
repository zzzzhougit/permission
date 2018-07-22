package com.yaozhou.permission.service.impl;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.yaozhou.permission.mapper.SysDeptMapper;
import com.yaozhou.permission.dto.DeptLevelDto;
import com.yaozhou.permission.model.SysDept;
import com.yaozhou.permission.service.SysTreeService;
import com.yaozhou.permission.util.LevelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Yao.Zhou
 * @since 2018/7/21 20:02
 */
@Service
public class SysTreeServiceImpl implements SysTreeService {

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Override
    public List<DeptLevelDto> deptTree() {
        List<SysDept> sysDepts = sysDeptMapper.getAllDept();
        List<DeptLevelDto> doList = sysDepts
                                        .stream()
                                        .map(sysDept -> DeptLevelDto.adapt(sysDept))
                                        .collect(toList());

        return deptListToTree(doList);
    }

    private List<DeptLevelDto> deptListToTree(List<DeptLevelDto> deptLevelDtoList) {
        if (CollectionUtils.isEmpty(deptLevelDtoList)) {
            return new LinkedList<>();
        }

        List<DeptLevelDto> rootDeptDotList = new LinkedList<>();
        Multimap<String, DeptLevelDto> allDeptLevelMap = LinkedListMultimap.create();
        deptLevelDtoList.forEach(dto -> {
            allDeptLevelMap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootDeptDotList.add(dto);
            }
        });

        Collections.sort(rootDeptDotList, deptSeqComparator);
        transFromDeptTree(rootDeptDotList, allDeptLevelMap);

        return rootDeptDotList;
    }

    private void transFromDeptTree(List<DeptLevelDto> deptDotList, Multimap<String, DeptLevelDto> allDeptLevelMap) {
        for (DeptLevelDto deptDto : deptDotList) {
            String nextLevel = LevelUtil.calculateLevel(deptDto.getLevel(), deptDto.getDeptId());

            List<DeptLevelDto> tempDtoList = (List<DeptLevelDto>) allDeptLevelMap.get(nextLevel);
            if (!CollectionUtils.isEmpty(tempDtoList)) {
                Collections.sort(tempDtoList, deptSeqComparator);
                deptDto.setNextLevelDeptList(tempDtoList);

                transFromDeptTree(tempDtoList, allDeptLevelMap);
            }
        } //end for
    }

    /**
     * 根据seq值排序dept
     */
    private static final Comparator<DeptLevelDto> deptSeqComparator = new Comparator<DeptLevelDto>() {
        @Override
        public int compare(DeptLevelDto o1, DeptLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

}
