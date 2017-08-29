package com.cy.dao.system;

import com.cy.entity.system.UserGroupInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 */
@Repository
public interface UserGroupInfoDAO {

    int insert(UserGroupInfo entity);

    int batchInsert(List<UserGroupInfo> list);

    int delete(Long id);

    int batchDelete(List<Long> list);

    int update(UserGroupInfo entity);

    UserGroupInfo getById(Long id);

    List<UserGroupInfo> getByIdList(List<Long> list);

}
