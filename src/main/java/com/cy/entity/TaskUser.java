package com.cy.entity;

import java.io.Serializable;

/**
 * Created by zxj on 2018-01-04 17:01:54.
 * task_user-任务关联用户表
 */
public class TaskUser implements Serializable {

    private static final long serialVersionUID = 3136285266284543531L;

    /** 主键,not null */
    private Long id;

    /** 任务id,not null */
    private Long taskId;

    /** 用户id,not null */
    private Long userId;

    /** 状态 参考:{@link com.cy.entity.system.enums.TaskUserState},not null */
    private String state;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}