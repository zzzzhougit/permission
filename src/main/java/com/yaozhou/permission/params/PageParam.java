package com.yaozhou.permission.params;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * @author Yao.Zhou
 * @since 2018/7/29 18:34
 */
@ToString
public class PageParam {

    @Getter
    @Setter
    @Min(value = 1, message = "pageNo不能小于1")
    private int pageNo = 1;

    @Getter
    @Setter
    @Min(value = 1, message = "pageSize不能小于1")
    @Max(value = 100, message = "pageSize不能大于100")
    private int pageSize = 10;

    @Setter
    private int offset;

    public int getOffset() {
        return (pageNo - 1) * pageSize;
    }

}
