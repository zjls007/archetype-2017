package com.cy.web.dto.result;

import com.cy.entity.Attachment;
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
    private List<Attachment> imgList;

    /**
     * 附件
     */
    private List<Attachment> attachmentList;

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

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public List<Attachment> getImgList() {
        return imgList;
    }

    public void setImgList(List<Attachment> imgList) {
        this.imgList = imgList;
    }
}
