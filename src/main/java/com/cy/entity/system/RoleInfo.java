package com.cy.entity.system;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 * t_role_info-角色信息表
 */
public class RoleInfo implements Serializable {

    private static final long serialVersionUID = 1468909215847375601L;

    /** 主键,not null */
    private Long id;

    /** 角色名称,not null */
    private String name;

    /** 角色编码 */
    private String code;

    /** 创建时间,not null */
    private Date createTime;

    /** 最后更新时间,not null */
    private Date lstUpdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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