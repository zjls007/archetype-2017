package com.cy.web.dto.param.system;

import com.cy.common.annotation.ParamValid;
import com.cy.entity.Task;
import com.cy.entity.TaskUser;
import com.cy.entity.system.UserInfo;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

/**
 * Created by hyl on 2018/1/6.
 */
@ParamValid
public class TaskSaveDTO implements Serializable {

    @Valid
    private Task task;

    @NotEmpty(message = "没有选择用户!")
    private List<Long> userIdList;

    /**
     * 图片
     */
    private List<String> imgList;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Long> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Long> userIdList) {
        this.userIdList = userIdList;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}
