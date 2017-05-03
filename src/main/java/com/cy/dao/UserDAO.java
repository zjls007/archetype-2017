package com.cy.dao;

import com.cy.entity.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zxj on 2017/5/3.
 */
@Repository
public interface UserDAO {

    int insert(UserInfo entity);

    int batchInsert(List<UserInfo> list);

    int delete(Long id);

    int batchDelete(List<Long> list);

    int update(UserInfo entity);

    UserInfo selectById(Long id);

}
