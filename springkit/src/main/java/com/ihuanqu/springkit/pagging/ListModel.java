package com.ihuanqu.springkit.pagging;

import lombok.Data;

/**
 * 列表模型
 *
 * @author kvh
 * @date 2017/10/30
 */
@Data
public class ListModel<T> {

    /**
     * 列表内容
     */
    private T list;
    /**
     * 总数
     */
    private long total;

    private int pageSize;

    private int pageOffset;
}
