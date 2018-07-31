package com.yaozhou.permission.dto;

import com.yaozhou.permission.model.SysAclModule;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Yao.Zhou
 * @since 2018/7/31 20:45
 */
@Getter
@Setter
@ToString
public class AclModuleLevelDto extends SysAclModule {

    List<AclModuleLevelDto> nextLevelAclModuleDtoList = new LinkedList<>();

    public static AclModuleLevelDto adapt(SysAclModule sysAclModule) {
        AclModuleLevelDto aclModuleLevelDto = new AclModuleLevelDto();
        BeanUtils.copyProperties(sysAclModule, aclModuleLevelDto);

        return aclModuleLevelDto;
    }

}
