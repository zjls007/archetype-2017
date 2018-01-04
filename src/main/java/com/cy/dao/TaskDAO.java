package com.cy.dao;

import com.cy.entity.Task;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by zxj on 2018-01-04 17:01:54.
 */
@Repository
public interface TaskDAO {

    int insert(Task entity);

    int batchInsert(List<Task> list);

    int deleteById(Long id);

    int batchDelete(List<Long> list);

    int updateByIdSelective(Task entity);

    int updateById(Task entity);

    Task getById(Long id);

    List<Task> getByIdList(List<Long> list);

    List<Task> list(Task entity);

    Task getByTaskNum(String taskNum);

    int countByTaskNum(String taskNum);

    List<Task> listByCreateUserId(Long createUserId);

}
