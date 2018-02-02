package com.cy.service.impl;

import com.cy.common.exception.ParamException;
import com.cy.common.exception.ValidException;
import com.cy.common.util.DateUtil;
import com.cy.dao.*;
import com.cy.entity.AttachmentRef;
import com.cy.entity.Task;
import com.cy.entity.TaskStateChange;
import com.cy.entity.TaskUser;
import com.cy.entity.system.UserInfo;
import com.cy.entity.system.enums.*;
import com.cy.service.GlobalCallback;
import com.cy.service.TaskService;
import com.cy.web.dto.param.system.TaskSaveDTO;
import com.cy.web.dto.result.TaskNoteDTO;
import com.cy.web.dto.result.TaskResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private TaskNoteDAO taskNoteDAO;

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
    public void batchDel(List<Long> list, Long currentUserId) {
        if (list == null || list.isEmpty()) {
            return;
        }
        // 批量删除时不给提示，单条删除会给出提示
        boolean tip = list.size() == 1;
        for (Long id : list) {
            Task task = taskDAO.getById(id);
            if (task == null) {
                if (tip) {
                    throw new ValidException("任务不存在,请刷新重试!");
                } else {
                    continue;
                }
            }
            if (task.getCreateUserId().longValue() != currentUserId) {
                if (tip) {
                    throw new ValidException("无权限删除!");
                } else {
                    continue;
                }
            }
            if (!TaskState.PUBLISH.getCode().equals(task.getState()) && !TaskState.TAKE.getCode().equals(task.getState())) {
                if (tip) {
                    throw new ValidException("当前状态不允许删除!");
                } else {
                    continue;
                }
            }
            taskDAO.deleteById(id);
            taskUserDAO.deleteByTaskId(id);
            taskStateChangeDAO.deleteByTaskId(id);
            attachmentRefDAO.deleteByTaskId(id);
        }
    }

    @Override
    public void begin(Long taskId, Long currentUserId) {
        if (taskId == null || currentUserId == null) {
            throw new ParamException("参数不能为空");
        }
        Task task = taskDAO.getById(taskId);
        if (task == null) {
            throw new ValidException("任务不存在!");
        } else if (!TaskState.TAKE.getCode().equals(task.getState())) {
            throw new ValidException("任务必须为认领状态!");
        } else if (DateUtil.getDate(new Date()).compareTo(DateUtil.getDate(task.getDueDate())) == 1) {
            throw new ValidException("任务已经过期，请设置延期时间!");
        }
        int result = taskUserDAO.countByUserIdAndTaskId(currentUserId, taskId);
        if (result == 0) {
            throw new ValidException("当前用户不是任务的认领人!");
        }
        // 更新任务状态为开始
        result = taskDAO.updateState(taskId, TaskState.BEGIN.getCode(), TaskState.TAKE.getCode());
        if (result  != 1) {
            throw new ValidException("任务状态不正确!");
        }
        doStateChange(taskId, currentUserId, TaskState.BEGIN.getCode());
    }

    @Override
    public List<TaskNoteDTO> initNote(Long taskId, Date dueDate) {
        TaskStateChange taskStateChange = taskStateChangeDAO.getByState(taskId, TaskState.BEGIN.getCode());
        Date startDate = DateUtil.getDate(taskStateChange.getCreateTime());
        dueDate = DateUtil.getDate(dueDate);
        taskNoteDAO.listByTaskId(taskId);
        List<TaskNoteDTO> list = new ArrayList<TaskNoteDTO>();
        do {
            TaskNoteDTO dto = new TaskNoteDTO();
            dto.setDate(startDate);
            list.add(dto);
            startDate = DateUtil.addDay(startDate, 1);
        } while (startDate.compareTo(dueDate) == -1);
        return list;
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
