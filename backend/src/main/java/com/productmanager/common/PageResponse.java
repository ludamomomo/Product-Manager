package com.productmanager.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页响应
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 数据列表
     */
    private List<T> records = new ArrayList<>();

    /**
     * 总数
     */
    private Long total;

    /**
     * 总页数
     */
    private Long pages;

    /**
     * 当前页码
     */
    private Long current;

    /**
     * 每页数量
     */
    private Long size;
}
