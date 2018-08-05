package com.yaozhou.permission.dto;

import com.yaozhou.permission.model.SysAcl;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

/**
 * @author Yao.Zhou
 * @since 2018/8/5 15:33
 */
@Getter
@Setter
@ToString
public class AclDto extends SysAcl {

    /**
     * 是否默认选中
     */
    private boolean checked = false;

    /**
     * 是否有权限操作
     */
    private boolean hasAcl = false;

    public static AclDto adapt(SysAcl sysAcl) {
        AclDto aclDto = new AclDto();
        BeanUtils.copyProperties(sysAcl, aclDto);

        return aclDto;
    }

}
