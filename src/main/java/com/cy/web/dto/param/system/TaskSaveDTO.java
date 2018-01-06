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
    @Valid
    private List<Long> userIdList;

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
}
