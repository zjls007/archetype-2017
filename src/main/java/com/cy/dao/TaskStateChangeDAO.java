package com.cy.dao;

import com.cy.entity.TaskStateChange;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by zxj on 2018-01-04 17:01:54.
 */
@Repository
public interface TaskStateChangeDAO {

    int insert(TaskStateChange entity);

    int batchInsert(List<TaskStateChange> list);

    int deleteById(Long id);

    int deleteByTaskId(Long id);

    int batchDelete(List<Long> list);

    int updateByIdSelective(TaskStateChange entity);

    int updateById(TaskStateChange entity);

    TaskStateChange getById(Long id);

    List<TaskStateChange> getByIdList(List<Long> list);

    List<TaskStateChange> list(TaskStateChange entity);

    List<TaskStateChange> listByTaskId(Long taskId);

}
