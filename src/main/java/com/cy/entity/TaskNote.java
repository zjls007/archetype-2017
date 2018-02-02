package com.cy.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2018-02-02 13:47:17.
 * task_note-任务笔记表
 */
public class TaskNote implements Serializable {

    private static final long serialVersionUID = -8684469183588365139L;

    /** 主键,not null */
    private Long id;

    /** 任务id,not null */
    private Long taskId;

    /** 日期,not null */
    private Date date;

    /** 备注,not null */
    private String remark;

    /** 操作用户id,not null */
    private Long operateUserId;

    /** ,not null */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Long operateUserId) {
        this.operateUserId = operateUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}