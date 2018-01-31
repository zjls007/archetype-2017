package com.cy.service.impl;

import com.cy.common.exception.ValidException;
import com.cy.dao.*;
import com.cy.entity.AttachmentRef;
import com.cy.entity.Task;
import com.cy.entity.TaskStateChange;
import com.cy.entity.TaskUser;
import com.cy.entity.system.UserInfo;
import com.cy.entity.system.enums.*;
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

    @Autowired
    private AttachmentDAO attachmentDAO;

    @Override
    public TaskResultDTO get(Long taskId) {
        TaskResultDTO result = new TaskResultDTO();
        result.setTask(taskDAO.getById(taskId));
        result.setTaskUserList(taskUserDAO.listByTaskId(taskId));
        result.setImgList(attachmentDAO.listByTask(taskId, AttachmentRefFileType.IMG.getCode()));
        result.setAttachmentList(attachmentDAO.listByTask(taskId, AttachmentRefFileType.ATTACHMENT.getCode()));
        return result;
    }

    @Override
    public void saveOrUpdate(TaskSaveDTO dto, UserInfo currentUser) {
        Long taskId = doTask(dto, currentUser);
        doUser(taskId, dto.getUserIdList(), dto.getTask().getType());
        doImg(dto.getImgList(), taskId, currentUser.getId());
        doAttachment(dto.getAttachmentMD5List(), taskId, currentUser.getId(), AttachmentRefFileType.ATTACHMENT.getCode());
    }

    private void doAttachment(List<String> attachmentMD5List, Long taskId, Long currentUserId, String fileType) {
        if (attachmentMD5List == null || attachmentMD5List.isEmpty()) {
            return;
        }
        List<AttachmentRef> list = new ArrayList<AttachmentRef>();
        for (String item : attachmentMD5List) {
            AttachmentRef ref = new AttachmentRef();
            ref.setFileId(item);
            ref.setFileType(fileType);
            ref.setRefId(taskId);
            ref.setRefType(AttachmentRefType.TASK.getCode());
            ref.setCreateUserId(currentUserId);
            list.add(ref);
        }
        attachmentRefDAO.batchInsert(list);
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

    private void doImg(List<String> imgList, Long taskId, Long currentUserId) {
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
        doAttachment(imgList, taskId, currentUserId, AttachmentRefFileType.IMG.getCode());
    }

    private void doStateChange(Long taskId, Long curUserId, String state) {
        TaskStateChange bean = new TaskStateChange();
        bean.setState(state);
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
            if (TaskType.ASSIGN.getCode().equals(task.getType())) {
                task.setState(TaskState.TAKE.getCode());
            }
            task.setCreateUserId(currentUser.getId());
            task.setCreateUserName(currentUser.getUserName());
            taskDAO.insert(task);
            id = task.getId();
            doStateChange(id, currentUser.getId(), TaskState.PUBLISH.getCode());
            if (TaskType.ASSIGN.getCode().equals(task.getType())) {
                doStateChange(id, currentUser.getId(), TaskState.TAKE.getCode());
            }
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
