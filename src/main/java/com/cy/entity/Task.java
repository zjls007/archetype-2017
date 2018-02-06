package com.cy.entity;

import com.cy.common.annotation.SelectVal;
import com.cy.common.util.SelectUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2018-01-07 08:58:36.
 * task-任务表
 */
public class Task implements Serializable {

    private static final long serialVersionUID = 873025102264243163L;

    /** 主键,not null */
    private Long id;

    /** 任务编号,not null */
    private String taskNum;

    /** 任务标题,not null */
    @NotBlank(message = "任务标题不能为空!")
    @Length(min = 2, max = 50, message = "任务标题长度必须在{min}-{max}位之间!")
    private String title;

    /** 任务内容,not null */
    @NotBlank(message = "任务内容不能为空!")
    @Length(min = 1, max = 500, message = "任务内容长度必须在{min}-{max}位之间!")
    private String content;

    /** 截止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "截止日期不能为空!")
    private Date dueDate;

    /** 任务状态 参考:{@link com.cy.entity.system.enums.TaskDifficult},not null */
    @NotBlank(message = "难度不能为空!")
    @Pattern(regexp = "(easy|normal|hard)", message = "难度不合法!")
    @SelectVal(key = SelectUtil.TASK_DIFFICULT)
    private String difficult;

    /** 类型 参考:{@link com.cy.entity.system.enums.TaskType},not null */
    @NotBlank(message = "类型不能为空!")
    @Pattern(regexp = "(assign|take)", message = "类型不合法!")
    @SelectVal(key = SelectUtil.TASK_TYPE)
    private String type;

    /** 任务状态 参考:{@link com.cy.entity.system.enums.TaskState},not null */
    @SelectVal(key = SelectUtil.TASK_STATE)
    private String state;

    /** 创建用户id,not null */
    private Long createUserId;

    /** 创建用户名称,not null */
    private String createUserName;

    /** 创建时间,not null */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
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