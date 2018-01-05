package com.cy.service.impl;

import com.cy.dao.TaskDAO;
import com.cy.dao.TaskStateChangeDAO;
import com.cy.dao.TaskUserDAO;
import com.cy.entity.Task;
import com.cy.entity.system.UserInfo;
import com.cy.entity.system.enums.TaskState;
import com.cy.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by zxj on 2018/1/5.
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskDAO taskDAO;

    @Autowired
    private TaskUserDAO taskUserDAO;

    @Autowired
    private TaskStateChangeDAO taskStateChangeDAO;

    @Override
    public void saveOrUpdate(Task task, UserInfo currentUser) {
        Long id = task.getId();

        if (id == null) {
            task.setState(TaskState.PUBLISH.getCode());
            task.setCreateUserId(currentUser.getId());
            task.setCreateUserName(currentUser.getUserName());
        } else {

        }
    }

}
