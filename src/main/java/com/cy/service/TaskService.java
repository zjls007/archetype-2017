package com.cy.service;

import com.cy.entity.Task;
import com.cy.entity.system.UserInfo;
import com.cy.web.dto.param.system.TaskNoteSaveDTO;
import com.cy.web.dto.param.system.TaskSaveDTO;
import com.cy.web.dto.result.TaskNoteDTO;
import com.cy.web.dto.result.TaskResultDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zxj on 2018/1/5.
 */
public interface TaskService {

    Map<String, Object> getStep(Long taskId);

    /**
     * 获取任务详情
     *
     * @param taskId
     * @return
     */
    TaskResultDTO get(Long taskId);

    /**
     * 保存或更新任务
     *
     * @param dto
     */
    void saveOrUpdate(TaskSaveDTO dto, UserInfo currentUser);

    /**
     * 批量删除
     *
     * @param list
     */
    void batchDel(List<Long> list, Long currentUserId);

    /**
     * 开始任务
     *
     * @param taskId
     * @param currentUserId
     */
    void begin(Long taskId, Long currentUserId);

    /**
     * 完成任务
     *
     * @param taskId
     * @param currentUserId
     */
    void complete(Long taskId, Long currentUserId);

    /**
     * 初始化笔记
     *
     * @param taskId
     * @param dueDate
     */
    List<TaskNoteDTO> initNote(Long taskId, String state, Date dueDate, boolean isTaskCreater);

    /**
     * 保存笔记
     * @param dto
     */
    void saveOrUpdateNotes(TaskNoteSaveDTO dto, Long userId);

}
