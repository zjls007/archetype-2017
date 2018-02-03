package com.cy.web.vo;

import com.cy.entity.Attachment;
import com.cy.entity.Task;
import com.cy.web.dto.result.TaskNoteDTO;

import java.util.List;

/**
 * Created by zxj on 2018/1/9.
 */
public class TaskDetailVO extends Task {

    private static final long serialVersionUID = 2030792575336539855L;

    private List<Select2ItemVO> userList;

    private List<ImgResultVO> imgList;

    private List<Attachment> attachmentList;

    private List<TaskNoteDTO> taskNoteDTOList;

    /**
     * 查看页面是否显示开始按钮
     */
    private boolean showBeginBtn;

    /**
     * 查看页面是否显示编辑按钮
     */
    private boolean showEditBtn;

    /**
     * 查看页面是否显示保存笔记按钮
     */
    private boolean showSaveNoteBtn;

    /**
     * 查看页面是否显示完成按钮
     */
    private boolean showCompleteBtn;

    public List<ImgResultVO> getImgList() {
        return imgList;
    }

    public void setImgList(List<ImgResultVO> imgList) {
        this.imgList = imgList;
    }

    public List<Select2ItemVO> getUserList() {
        return userList;
    }

    public void setUserList(List<Select2ItemVO> userList) {
        this.userList = userList;
    }

    public List<Attachment> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<Attachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public boolean isShowBeginBtn() {
        return showBeginBtn;
    }

    public void setShowBeginBtn(boolean showBeginBtn) {
        this.showBeginBtn = showBeginBtn;
    }

    public List<TaskNoteDTO> getTaskNoteDTOList() {
        return taskNoteDTOList;
    }

    public void setTaskNoteDTOList(List<TaskNoteDTO> taskNoteDTOList) {
        this.taskNoteDTOList = taskNoteDTOList;
    }

    public boolean isShowEditBtn() {
        return showEditBtn;
    }

    public void setShowEditBtn(boolean showEditBtn) {
        this.showEditBtn = showEditBtn;
    }

    public boolean isShowSaveNoteBtn() {
        return showSaveNoteBtn;
    }

    public void setShowSaveNoteBtn(boolean showSaveNoteBtn) {
        this.showSaveNoteBtn = showSaveNoteBtn;
    }

    public boolean isShowCompleteBtn() {
        return showCompleteBtn;
    }

    public void setShowCompleteBtn(boolean showCompleteBtn) {
        this.showCompleteBtn = showCompleteBtn;
    }
}
