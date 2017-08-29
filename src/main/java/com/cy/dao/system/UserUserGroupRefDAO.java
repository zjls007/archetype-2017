package com.cy.dao.system;

import com.cy.entity.system.UserUserGroupRef;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 */
@Repository
public interface UserUserGroupRefDAO {

    int insert(UserUserGroupRef entity);

    int batchInsert(List<UserUserGroupRef> list);

    int delete(Long id);

    int batchDelete(List<Long> list);

    int update(UserUserGroupRef entity);

    UserUserGroupRef getById(Long id);

    List<UserUserGroupRef> getByIdList(List<Long> list);

}
