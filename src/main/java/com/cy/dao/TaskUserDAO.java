package com.cy.dao;

import com.cy.entity.TaskUser;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zxj on 2018-01-04 17:01:54.
 */
@Repository
public interface TaskUserDAO {

    int insert(TaskUser entity);

    int batchInsert(List<TaskUser> list);

    int deleteById(Long id);

    int deleteByTaskId(Long id);

    int batchDelete(List<Long> list);

    int updateByIdSelective(TaskUser entity);

    int updateById(TaskUser entity);

    TaskUser getById(Long id);

    List<TaskUser> getByIdList(List<Long> list);

    List<TaskUser> list(TaskUser entity);

    TaskUser getByUserIdAndTaskId(@Param("userId") Long userId, @Param("taskId") Long taskId);

    int countByUserIdAndTaskId(@Param("userId") Long userId, @Param("taskId") Long taskId);

    List<TaskUser> listByTaskId(Long taskId);

}
