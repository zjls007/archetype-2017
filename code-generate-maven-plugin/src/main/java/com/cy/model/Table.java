package com.cy.model;

import java.util.List;

/**
 * Created by zxj on 2017/5/2.
 */
public class Table {

    /**
     * 表名
     */
    private String name;

    /**
     * 主键名称
     */
    private String primaryKeyName;

    private List<Column> columnList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }
}
