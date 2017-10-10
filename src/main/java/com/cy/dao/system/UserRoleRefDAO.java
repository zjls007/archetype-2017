package com.cy.dao.system;

import com.cy.entity.system.UserRoleRef;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 */
@Repository
public interface UserRoleRefDAO {

    int insert(UserRoleRef entity);

    int batchInsert(List<UserRoleRef> list);

    int delete(Long id);

    int batchDelete(List<Long> list);

    int update(UserRoleRef entity);

    UserRoleRef getById(Long id);

    List<UserRoleRef> getByIdList(List<Long> list);

    List<Long> getByUserInfoId(Long userInfoId);

    List<Long> getByRoleInfoId(Long roleInfoId);

    int deleteByUserInfoId(Long userInfoId);

}
