package com.cy.dto;

import java.io.Serializable;

/**
 * Created by zxj on 2017/6/26.
 */
public class PropertyDTO implements Serializable {

    private static final long serialVersionUID = 1224855597167946728L;

    private Boolean notNull;

    private Boolean primaryKey;

    /**
     * 属性注释
     */
    private String remark;

    /**
     * 属性类型名称
     */
    private String typeName;

    /**
     * 属性类型全称
     */
    private String typeAllName;

    /**
     * 属性数据库字段名
     */
    private String columnName;

    /**
     * 属性名
     */
    private String propertyName;

    /**
     * 属性get方法名称
     */
    private String getMethodName;

    /**
     * 属性set方法名称
     */
    private String setMethodName;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeAllName() {
        return typeAllName;
    }

    public void setTypeAllName(String typeAllName) {
        this.typeAllName = typeAllName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getGetMethodName() {
        return getMethodName;
    }

    public void setGetMethodName(String getMethodName) {
        this.getMethodName = getMethodName;
    }

    public String getSetMethodName() {
        return setMethodName;
    }

    public void setSetMethodName(String setMethodName) {
        this.setMethodName = setMethodName;
    }

    public Boolean getNotNull() {
        return notNull;
    }

    public void setNotNull(Boolean notNull) {
        this.notNull = notNull;
    }

    public Boolean getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }
}
