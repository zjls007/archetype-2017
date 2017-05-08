package com.cy.common;

/**
 * Created by zxj on 2017/5/8.
 */
public class PageInfo {

    private Long page;

    private Long rows;

    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
