package com.cy.entity;

import com.cy.common.annotation.ParamValid;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2018-01-04 17:01:54.
 * task-任务表
 */
@ParamValid
public class Task implements Serializable {

    private static final long serialVersionUID = 747013178655742732L;

    /** 主键,not null */
    private Long id;

    /** 任务编号,not null */
    private String taskNum;

    /** 任务标题,not null */
    @NotBlank(message = "任务标题不能为空!")
    private String title;

    /** 任务内容,not null */
    @NotBlank(message = "任务内容不能为空!")
    private String content;

    /** 类型 参考:{@link com.cy.entity.system.enums.TaskType},not null */
    @Pattern(regexp = "(assign|take)", message = "类型不合法!")
    private String type;

    /** 任务状态 参考:{@link com.cy.entity.system.enums.TaskState},not null */
    private String state;

    /** 创建用户id,not null */
    private Long createUserId;

    /** 创建用户名称,not null */
    private String createUserName;

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

    public String getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(String taskNum) {
        this.taskNum = taskNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
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