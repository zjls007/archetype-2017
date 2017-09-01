package com.cy.entity.system;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2017-08-29 10:32:26.
 * t_menu_info-菜单信息表
 */
public class MenuInfo implements Serializable {

    private static final long serialVersionUID = -1785069688580960166L;

    /** 主键,not null */
    private Long id;

    /** 菜单名称,not null */
    private String name;

    /** 菜单url,not null */
    private String url;

    /** 父级菜单id,无填写0,not null */
    @JsonProperty("_parentId")
    private Long parentId;

    /** 排序编号,not null */
    private Integer sortNum;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }
}