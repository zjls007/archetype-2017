package com.cy.web.vo;

import com.cy.entity.Task;

import java.util.List;

/**
 * Created by zxj on 2018/1/9.
 */
public class TaskDetailVO extends Task {

    private static final long serialVersionUID = 2030792575336539855L;

    private List<Select2ItemVO> userList;

    private List<ImgResultVO> imgList;

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
}
