package com.cy.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2018-01-08 10:42:01.
 * attachment-附件表
 */
public class Attachment implements Serializable {

    private static final long serialVersionUID = -679863194446843981L;

    /** ,not null */
    private String id;

    /** ,not null */
    private byte[] data;

    /** 创建用户id,not null */
    private Long createUserId;

    /** 创建时间,not null */
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}