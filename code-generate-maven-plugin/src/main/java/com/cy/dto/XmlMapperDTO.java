package com.cy.dto;

import java.util.List;

/**
 * Created by zxj on 2017/5/3.
 */
public class XmlMapperDTO {

    private String modelFullName;

    private String daoFullName;

    private String tableName;

    private String primaryKeyName;

    private List<String> noIdColunmList;

    private List<String> columnList;

    public String getModelFullName() {
        return modelFullName;
    }

    public void setModelFullName(String modelFullName) {
        this.modelFullName = modelFullName;
    }

    public String getDaoFullName() {
        return daoFullName;
    }

    public void setDaoFullName(String daoFullName) {
        this.daoFullName = daoFullName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getNoIdColunmList() {
        return noIdColunmList;
    }

    public void setNoIdColunmList(List<String> noIdColunmList) {
        this.noIdColunmList = noIdColunmList;
    }

    public List<String> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<String> columnList) {
        this.columnList = columnList;
    }

    public String getPrimaryKeyName() {
        return primaryKeyName;
    }

    public void setPrimaryKeyName(String primaryKeyName) {
        this.primaryKeyName = primaryKeyName;
    }
}
