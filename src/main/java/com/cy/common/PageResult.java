package com.cy.common;

import com.github.pagehelper.Page;

/**
 * Created by zxj on 2017/5/8.
 */
public class PageResult {

    private Long total;

    private Object rows;

    public PageResult(Page<?> page) {
        this.total = page.getTotal();
        this.rows = page.getResult();
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

}
