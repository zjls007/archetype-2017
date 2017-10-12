package com.cy.entity.system;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 * t_operation_info-操作表
 */
public class OperationInfo implements Serializable {

    private static final long serialVersionUID = 881508148576665499L;

    /** 主键,not null */
    private Long id;

    /** 操作名称,not null */
    private String name;

    /** 操作编码,not null */
    private String code;

    /** ,not null */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** ,not null */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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