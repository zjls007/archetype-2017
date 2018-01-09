package com.cy.service.impl;

import com.cy.common.exception.ValidException;
import com.cy.dao.AttachmentRefDAO;
import com.cy.dao.TaskDAO;
import com.cy.dao.TaskStateChangeDAO;
import com.cy.dao.TaskUserDAO;
import com.cy.entity.*;
import com.cy.entity.system.UserInfo;
import com.cy.entity.system.enums.AttachmentRefType;
import com.cy.entity.system.enums.TaskState;
import com.cy.entity.system.enums.TaskType;
import com.cy.entity.system.enums.TaskUserState;
import com.cy.service.TaskService;
import com.cy.web.dto.param.system.TaskSaveDTO;
import com.cy.web.dto.result.TaskResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Autowired
    private AttachmentRefDAO attachmentRefDAO;

    @Override
    public TaskResultDTO get(Long taskId) {
        TaskResultDTO result = new TaskResultDTO();
        result.setTask(taskDAO.getById(taskId));
        result.setTaskUserList(taskUserDAO.listByTaskId(taskId));
        result.setImgList(attachmentRefDAO.listByRefIdAndRefType(taskId, AttachmentRefType.TASK.getCode()));
        return result;
    }

    @Override
    public void saveOrUpdate(TaskSaveDTO dto, UserInfo currentUser) {
        Long taskId = doTask(dto, currentUser);
        doUser(taskId, dto.getUserIdList(), dto.getTask().getType());
        doImg(dto.getImgList(), taskId, currentUser);
    }

    @Override
    public void batchDel(List<Long> list) {
        for (Long id : list) {
            Task task = taskDAO.getById(id);
            if (task == null || !TaskState.PUBLISH.getCode().equals(task.getState())) {
                continue;
            }
            taskDAO.deleteById(id);
            taskUserDAO.deleteByTaskId(id);
            taskStateChangeDAO.deleteByTaskId(id);
            attachmentRefDAO.deleteByTaskId(id);
        }
    }

    private void doImg(List<String> imgList, Long taskId, UserInfo currentUser) {
        attachmentRefDAO.deleteByTaskId(taskId);
        if (imgList == null || imgList.isEmpty()) {
            return;
        }
        for (int i = imgList.size() - 1; i >= 0; i--) {
            if (StringUtils.isEmpty(imgList.get(i))) {
                imgList.remove(i);
            }
        }
        if (imgList.isEmpty()) {
            return;
        }
        List<AttachmentRef> list = new ArrayList<AttachmentRef>();
        for (String item : imgList) {
            AttachmentRef ref = new AttachmentRef();
            ref.setFileId(item);
            ref.setRefId(taskId);
            ref.setRefType(AttachmentRefType.TASK.getCode());
            ref.setCreateUserId(currentUser.getId());
            list.add(ref);
        }
        attachmentRefDAO.batchInsert(list);
    }

    private void doStateChange(Long taskId, Long curUserId) {
        TaskStateChange bean = new TaskStateChange();
        bean.setState(TaskState.PUBLISH.getCode());
        bean.setTaskId(taskId);
        bean.setOperateUserId(curUserId);
        taskStateChangeDAO.insert(bean);
    }

    private void doUser(Long taskId, List<Long> userIdList, String type) {
        taskUserDAO.deleteByTaskId(taskId);
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
            doStateChange(id, currentUser.getId());
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
