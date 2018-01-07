package com.cy.service.impl;

import com.cy.common.exception.ValidException;
import com.cy.dao.TaskDAO;
import com.cy.dao.TaskStateChangeDAO;
import com.cy.dao.TaskUserDAO;
import com.cy.entity.Task;
import com.cy.entity.TaskStateChange;
import com.cy.entity.TaskUser;
import com.cy.entity.system.UserInfo;
import com.cy.entity.system.enums.TaskState;
import com.cy.entity.system.enums.TaskType;
import com.cy.entity.system.enums.TaskUserState;
import com.cy.service.TaskService;
import com.cy.web.dto.param.system.TaskSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public void saveOrUpdate(TaskSaveDTO dto, UserInfo currentUser) {
        Long taskId = doTask(dto, currentUser);
        doUser(taskId, dto.getUserIdList(), dto.getTask().getType());
        doStateChange(taskId, currentUser.getId());
    }

    private void doStateChange(Long taskId, Long curUserId) {
        TaskStateChange bean = new TaskStateChange();
        bean.setState(TaskState.PUBLISH.getCode());
        bean.setTaskId(taskId);
        bean.setOperateUserId(curUserId);
        taskStateChangeDAO.insert(bean);
    }

    private void doUser(Long taskId, List<Long> userIdList, String type) {
        List<TaskUser> list = new ArrayList<TaskUser>();
        for (Long userId: userIdList) {
            TaskUser taskUser = new TaskUser();
            taskUser.setTaskId(taskId);
            taskUser.setUserId(userId);
            if (TaskType.ASSIGN.getCode().equals(type)) {
                taskUser.setState(TaskUserState.ASSIGN.getCode());
            } else if (TaskType.TAKE.getCode().equals(type)) {
                taskUser.setState(TaskUserState.TAKE.getCode());
            }
            list.add(taskUser);
        }
        taskUserDAO.batchInsert(list);
    }

    private Long doTask(TaskSaveDTO dto, UserInfo currentUser) {
        Task task = dto.getTask();
        Long id = task.getId();

        if (id == null) {
            task.setTaskNum("T"+System.currentTimeMillis());
            task.setState(TaskState.PUBLISH.getCode());
            task.setCreateUserId(currentUser.getId());
            task.setCreateUserName(currentUser.getUserName());
            taskDAO.insert(task);
            id = task.getId();
        } else {
            Task db = taskDAO.getById(id);
            if (!TaskState.PUBLISH.getCode().equals(db.getState())) {
                throw new ValidException("必须为发布状态才能修改!");
            }
            taskDAO.updateById(task);
        }
        return id;
    }

}
