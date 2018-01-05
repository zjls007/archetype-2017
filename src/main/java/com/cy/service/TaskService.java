package com.cy.service;

import com.cy.entity.Task;
import com.cy.entity.system.UserInfo;

/**
 * Created by zxj on 2018/1/5.
 */
public interface TaskService {

    /**
     * 保存或更新任务
     *
     * @param task 任务
     * @param currentUser 当前用户
     */
    void saveOrUpdate(Task task, UserInfo currentUser);

}
