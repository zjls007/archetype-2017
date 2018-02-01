package com.cy.service;

import com.cy.entity.Task;
import com.cy.entity.system.UserInfo;
import com.cy.web.dto.param.system.TaskSaveDTO;
import com.cy.web.dto.result.TaskResultDTO;

import java.util.List;

/**
 * Created by zxj on 2018/1/5.
 */
public interface TaskService {

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

}
