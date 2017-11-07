package com.cy.model;

import java.util.List;
import java.util.Map;

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

    /**
     * 唯一列map
     */
    private Map<String, List<String>> uniKeyMap;

    /**
     * 索引列map
     */
    private Map<String, List<String>> indexKeyMap;

    /**
     * 表备注
     */
    private String remark;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Map<String, List<String>> getUniKeyMap() {
        return uniKeyMap;
    }

    public void setUniKeyMap(Map<String, List<String>> uniKeyMap) {
        this.uniKeyMap = uniKeyMap;
    }

    public Map<String, List<String>> getIndexKeyMap() {
        return indexKeyMap;
    }

    public void setIndexKeyMap(Map<String, List<String>> indexKeyMap) {
        this.indexKeyMap = indexKeyMap;
    }
}
