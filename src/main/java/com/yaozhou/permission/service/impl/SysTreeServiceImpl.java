package com.yaozhou.permission.service.impl;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.yaozhou.permission.dto.AclDto;
import com.yaozhou.permission.dto.AclModuleLevelDto;
import com.yaozhou.permission.mapper.SysAclModuleMapper;
import com.yaozhou.permission.mapper.SysDeptMapper;
import com.yaozhou.permission.dto.DeptLevelDto;
import com.yaozhou.permission.model.SysAcl;
import com.yaozhou.permission.model.SysAclModule;
import com.yaozhou.permission.model.SysDept;
import com.yaozhou.permission.service.*;
import com.yaozhou.permission.util.LevelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author Yao.Zhou
 * @since 2018/7/21 20:02
 */
@Service
public class SysTreeServiceImpl implements SysTreeService {

    @Autowired
    private SysAclService sysAclService;
    @Autowired
    private SysCoreService sysCoreService;
    @Autowired
    private SysDeptService sysDeptService;
    @Autowired
    private SysAclModuleService sysAclModuleService;

    //============================================================
    //Role Tree

    @Override
    public List<AclModuleLevelDto> roleTree(int userId, int roleId) throws Exception {
        List<SysAcl> userAclList = sysCoreService.getUserAclList(userId);
        List<SysAcl> roleAclList = sysCoreService.getRoleAclList(roleId);

        //系统所有权限点dto
        List<AclDto> aclDtoList = new LinkedList<>();
        //用户所有权限点aclId集合
        Set<Integer> userAclIdSet = userAclList.stream().map(e -> e.getAclId()).collect(Collectors.toSet());
        //角色所有权限点aclId合集
        Set<Integer> roleAclIdSet = roleAclList.stream().map(e -> e.getAclId()).collect(Collectors.toSet());

        //所有权限点集合
        List<SysAcl> allSysAcl = sysAclService.getAll();
        for (SysAcl sysAcl : allSysAcl) {
            AclDto aclDto = AclDto.adapt(sysAcl);
            if (userAclIdSet.contains(sysAcl.getAclId())) {
                aclDto.setHasAcl(true);
            }
            if (roleAclIdSet.contains(sysAcl.getAclId())) {
                aclDto.setChecked(true);
            }

            aclDtoList.add(aclDto);
        }

        return aclListToTree(aclDtoList);
    }

    /**
     *
     * @param aclDtoList
     * @return
     */
    private List<AclModuleLevelDto> aclListToTree(List<AclDto> aclDtoList) throws Exception {
        if (CollectionUtils.isEmpty(aclDtoList)) {

            return new ArrayList<>();
        }

        List<AclModuleLevelDto> aclModuleLevelDtoList = aclModuleTree();
        Multimap<Integer, AclDto> moduleIdAclMap = LinkedListMultimap.create();
        for (AclDto acl : aclDtoList) {
            if (acl.getStatus() == 1) {
                moduleIdAclMap.put(acl.getAclModuleId(), acl);
            }
        }

        bindAclsWithOrder(aclModuleLevelDtoList, moduleIdAclMap);

        return aclModuleLevelDtoList;
    }

    private void bindAclsWithOrder(List<AclModuleLevelDto> aclModuleLevelDtoList, Multimap<Integer, AclDto> moduleIdAclMap) {
        if (CollectionUtils.isEmpty(aclModuleLevelDtoList)) {

            return ;
        }

        for (AclModuleLevelDto aclModuleLevelDto : aclModuleLevelDtoList) {
            List<AclDto> aclDtoList = (List<AclDto>) moduleIdAclMap.get(aclModuleLevelDto.getAclModuleId());
            if (!CollectionUtils.isEmpty(aclDtoList)) {
                Collections.sort(aclDtoList, aclSeqComparator);

                aclModuleLevelDto.setAclList(aclDtoList);
            }

            bindAclsWithOrder(aclModuleLevelDto.getNextLevelAclModuleDtoList(), moduleIdAclMap);
        }
    }

    private static final Comparator<AclDto> aclSeqComparator = (o1, o2) -> o1.getSeq() - o2.getSeq();

    //============================================================
    //AclModule Tree

    @Override
    public List<AclModuleLevelDto> aclModuleTree() throws Exception {
        List<AclModuleLevelDto> allAclModuleLevelDto = sysAclModuleService.getAll()
                                    .stream()
                                    .map(aclModule -> AclModuleLevelDto.adapt(aclModule))
                                    .collect(toList());

        return aclModuleLevelDListToTree(allAclModuleLevelDto);
    }

    private List<AclModuleLevelDto> aclModuleLevelDListToTree(List<AclModuleLevelDto> allAclModuleLevelDto) {
        if (CollectionUtils.isEmpty(allAclModuleLevelDto)) {

            return new LinkedList<>();
        }

        List<AclModuleLevelDto> rootAclModuleDto = new LinkedList<>();
        Multimap<String, AclModuleLevelDto> allAclModuleLevelMap = LinkedListMultimap.create();
        allAclModuleLevelDto.forEach(dto -> {
            allAclModuleLevelMap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootAclModuleDto.add(dto);
            }
        });

        Collections.sort(rootAclModuleDto, aclModuleSeqComparator);
        transFromAclModuleTree(rootAclModuleDto, allAclModuleLevelMap);

        return rootAclModuleDto;
    }

    private void transFromAclModuleTree(List<AclModuleLevelDto> dtoList, Multimap<String, AclModuleLevelDto> allAclModuleLevelMap) {
        for (AclModuleLevelDto dto : dtoList) {
            String nextLevel = LevelUtil.calculateLevel(dto.getLevel(), dto.getAclModuleId());

            List<AclModuleLevelDto> tempDtoList = (List<AclModuleLevelDto>) allAclModuleLevelMap.get(nextLevel);
            if (!CollectionUtils.isEmpty(tempDtoList)) {
                Collections.sort(tempDtoList, aclModuleSeqComparator);
                dto.setNextLevelAclModuleDtoList(tempDtoList);

                transFromAclModuleTree(tempDtoList, allAclModuleLevelMap);
            }
        } //end for
    }

    /**
     * 根据seq值排序AclModule
     */
    private static final Comparator<AclModuleLevelDto> aclModuleSeqComparator = (o1, o2) -> o1.getSeq() - o2.getSeq();

    //============================================================
    //Dept Tree

    @Override
    public List<DeptLevelDto> deptTree() {
        List<DeptLevelDto> doList = sysDeptService.getAll()
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

    private void transFromDeptTree(List<DeptLevelDto> dtoList, Multimap<String, DeptLevelDto> allDeptLevelMap) {
        for (DeptLevelDto dto : dtoList) {
            String nextLevel = LevelUtil.calculateLevel(dto.getLevel(), dto.getDeptId());

            List<DeptLevelDto> tempDtoList = (List<DeptLevelDto>) allDeptLevelMap.get(nextLevel);
            if (!CollectionUtils.isEmpty(tempDtoList)) {
                Collections.sort(tempDtoList, deptSeqComparator);
                dto.setNextLevelDeptList(tempDtoList);

                transFromDeptTree(tempDtoList, allDeptLevelMap);
            }
        } //end for
    }

    /**
     * 根据seq值排序dept
     */
    private static final Comparator<DeptLevelDto> deptSeqComparator = (o1, o2) -> o1.getSeq() - o2.getSeq();

}
