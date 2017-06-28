package com.cy.dto;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Properties;

/**
 * Created by zxj on 2017/6/26.
 */
public class BaseDTO  {

    /**
     * 配置文件信息
     */
    private Properties config;

    /**
     * 作者
     */
    private String auth="";

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

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
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
}
