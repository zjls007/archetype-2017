package com.cy.web.dto.result;

import com.cy.entity.AttachmentRef;
import com.cy.entity.Task;
import com.cy.entity.TaskUser;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zxj on 2018/1/8.
 */
public class TaskResultDTO implements Serializable {

    private static final long serialVersionUID = -2922751097686045030L;

    /**
     * 任务
     */
    private Task task;

    /**
     * 用户
     */
    private List<TaskUser> taskUserList;

    /**
     * 图片
     */
    private List<AttachmentRef> imgList;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<TaskUser> getTaskUserList() {
        return taskUserList;
    }

    public void setTaskUserList(List<TaskUser> taskUserList) {
        this.taskUserList = taskUserList;
    }

    public List<AttachmentRef> getImgList() {
        return imgList;
    }

    public void setImgList(List<AttachmentRef> imgList) {
        this.imgList = imgList;
    }
}
