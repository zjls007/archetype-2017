package com.cy.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2018-01-08 16:33:37.
 * attachment_ref-附件关联表
 */
public class AttachmentRef implements Serializable {

    private static final long serialVersionUID = 7417536909348017899L;

    /** 主键,not null */
    private Long id;

    /** 文件id,not null */
    private String fileId;

    /** 关联id,not null */
    private Long refId;

    /** 关联类型（task-任务、other-其他）,not null */
    private String refType;

    /** 创建用户id,not null */
    private Long createUserId;

    /** 创建时间,not null */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
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