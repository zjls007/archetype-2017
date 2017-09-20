package com.cy.common;

import com.cy.entity.system.RoleInfo;

/**
 * Created by zxj on 2017/5/8.
 */
public class PageInfo<T>{

    private Long page;

    private Long rows;

    private T data;

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getRows() {
        return rows;
    }

    public void setRows(Long rows) {
        this.rows = rows;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
