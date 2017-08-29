package com.cy.dao.system;

import com.cy.entity.system.UserGroupRoleRef;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 */
@Repository
public interface UserGroupRoleRefDAO {

    int insert(UserGroupRoleRef entity);

    int batchInsert(List<UserGroupRoleRef> list);

    int delete(Long id);

    int batchDelete(List<Long> list);

    int update(UserGroupRoleRef entity);

    UserGroupRoleRef getById(Long id);

    List<UserGroupRoleRef> getByIdList(List<Long> list);

}
