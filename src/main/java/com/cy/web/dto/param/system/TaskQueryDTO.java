package com.cy.web.dto.param.system;

import com.cy.entity.Task;

import java.util.Date;

/**
 * Created by zxj on 2018/1/9.
 */
public class TaskQueryDTO extends Task {

    private static final long serialVersionUID = -6702905306573390045L;

    /** 截止日期开始 */
    private Date dueDateBegin;

    /** 截止日期结束 */
    private Date dueDateEnd;

    /** 注册时间开始 */
    private Date createTimeBegin;

    /** 注册时间结束 */
    private Date createTimeEnd;

    public Date getDueDateBegin() {
        return dueDateBegin;
    }

    public void setDueDateBegin(Date dueDateBegin) {
        this.dueDateBegin = dueDateBegin;
    }

    public Date getDueDateEnd() {
        return dueDateEnd;
    }

    public void setDueDateEnd(Date dueDateEnd) {
        this.dueDateEnd = dueDateEnd;
    }

    public Date getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(Date createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }
}
