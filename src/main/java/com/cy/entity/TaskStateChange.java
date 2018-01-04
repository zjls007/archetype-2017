package com.cy.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2018-01-04 17:01:54.
 * task_state_change-状态变更记录表
 */
public class TaskStateChange implements Serializable {

    private static final long serialVersionUID = 1632067774554733316L;

    /** 主键,not null */
    private Long id;

    /** 任务id,not null */
    private Long taskId;

    /** 参考task表state字段,not null */
    private String state;

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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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