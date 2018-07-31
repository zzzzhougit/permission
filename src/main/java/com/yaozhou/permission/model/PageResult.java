package com.yaozhou.permission.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

/**
 * 分页查询结果集
 * @author Yao.Zhou
 * @since 2018/7/29 18:51
 */
@Getter
@Setter
@ToString
@Builder
public class PageResult<T> {

    private int total;

    private int pageNo;

    private int pageSize;

    private List<T> data;

}
