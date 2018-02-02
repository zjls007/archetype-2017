package com.cy.dao;

import com.cy.entity.TaskNote;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * Created by zxj on 2018-02-02 13:47:17.
 */
@Repository
public interface TaskNoteDAO {

    int insert(TaskNote entity);

    int batchInsert(List<TaskNote> list);

    int deleteById(Long id);

    int batchDelete(List<Long> list);

    int updateByIdSelective(TaskNote entity);

    int updateById(TaskNote entity);

    TaskNote getById(Long id);

    List<TaskNote> getByIdList(List<Long> list);

    List<TaskNote> list(TaskNote entity);

    List<TaskNote> listByTaskId(Long taskId);

}
