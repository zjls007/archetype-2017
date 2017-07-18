package com.cy.dto;

import java.util.*;

/**
 * Created by zxj on 2017/6/26.
 */
public class BaseDTO  {

    /**
     * test
     */
    private List<String> setMethodInvokeStrList = new ArrayList<String>();

    /**
     * 配置文件信息
     */
    private Properties config;

    /**
     * 序列化id(用户替换页面)
     */
    private String serialVersionUID = "1L";

    /**
     * 生成时间
     */
    private Date date = new Date();

    /**
     * 引入类型
     */
    private LinkedHashSet<String> importTypeList = new LinkedHashSet<String>();

    /**
     * 表名称
     */
    private String tableName;

    /**
     * 表别名
     */
    private String tableAlias;

    /**
     * java对象名称
     */
    private String beanName;

    /**
     * 表备注
     */
    private String tableRemark;

    /**
     * java属性集合
     */
    private List<PropertyDTO> propertyList;

    /**
     * 唯一列索引
     */
    private List<List<PropertyDTO>> uniKeyList;

    /**
     * 主键字段类型
     */
    private String primaryKeyType;

    /**
     * 主键字段名
     */
    private String primaryKeyColumnName;

    /**
     * 主键属性名
     */
    private String primaryKeyPropertyName;

    public String getPrimaryKeyPropertyName() {
        return primaryKeyPropertyName;
    }

    public void setPrimaryKeyPropertyName(String primaryKeyPropertyName) {
        this.primaryKeyPropertyName = primaryKeyPropertyName;
    }

    public String getPrimaryKeyColumnName() {
        return primaryKeyColumnName;
    }

    public void setPrimaryKeyColumnName(String primaryKeyColumnName) {
        this.primaryKeyColumnName = primaryKeyColumnName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getTableRemark() {
        return tableRemark;
    }

    public void setTableRemark(String tableRemark) {
        this.tableRemark = tableRemark;
    }

    public List<PropertyDTO> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<PropertyDTO> propertyList) {
        this.propertyList = propertyList;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LinkedHashSet<String> getImportTypeList() {
        return importTypeList;
    }

    public void setImportTypeList(LinkedHashSet<String> importTypeList) {
        this.importTypeList = importTypeList;
    }

    public String getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setSerialVersionUID(String serialVersionUID) {
        this.serialVersionUID = serialVersionUID;
    }

    public Properties getConfig() {
        return config;
    }

    public void setConfig(Properties config) {
        this.config = config;
    }

    public String getPrimaryKeyType() {
        return primaryKeyType;
    }

    public void setPrimaryKeyType(String primaryKeyType) {
        this.primaryKeyType = primaryKeyType;
    }

    public List<String> getSetMethodInvokeStrList() {
        return setMethodInvokeStrList;
    }

    public void setSetMethodInvokeStrList(List<String> setMethodInvokeStrList) {
        this.setMethodInvokeStrList = setMethodInvokeStrList;
    }

    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    public List<List<PropertyDTO>> getUniKeyList() {
        return uniKeyList;
    }

    public void setUniKeyList(List<List<PropertyDTO>> uniKeyList) {
        this.uniKeyList = uniKeyList;
    }
}
