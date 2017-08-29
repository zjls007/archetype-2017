package com.cy.entity.system;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 * t_user_group_info-用户组信息表
 */
public class UserGroupInfo implements Serializable {

    private static final long serialVersionUID = -1527720615291919816L;

    /** 主键,not null */
    private Long id;

    /** 组名称,not null */
    private String name;

    /** 父级工作组id，为空时填0,not null */
    private Long parentId;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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