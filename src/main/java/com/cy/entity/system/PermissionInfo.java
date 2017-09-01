package com.cy.entity.system;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 * t_permission_info-权限表
 */
public class PermissionInfo implements Serializable {

    private static final long serialVersionUID = 974874450734662110L;

    /** 主键,not null */
    private Long id;

    /** 类型（menu-菜单、page-页面元素）,not null */
    private String type;

    /** 关联的数据id,not null */
    private Long dataId;

    /** 权限名称,not null */
    private String name;

    /** 权限code,not null */
    private String code;

    /** ,not null */
    private Date createTime;

    /** ,not null */
    private Date lstUpdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLstUpdTime() {
        return lstUpdTime;
    }

    public void setLstUpdTime(Date lstUpdTime) {
        this.lstUpdTime = lstUpdTime;
    }

}