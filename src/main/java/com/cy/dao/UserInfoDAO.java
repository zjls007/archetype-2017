package com.cy.dao;

import com.cy.entity.UserInfo;
import com.cy.web.dto.UserInfoListDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zxj on 2017-05-05 16:29:06.
 */
@Repository
public interface UserInfoDAO {

    int insert(UserInfo entity);

    int batchInsert(List<UserInfo> list);

    int delete(Long id);

    int batchDelete(List<Long> list);

    int update(UserInfo entity);

    UserInfo selectById(Long id);

    UserInfo selectByUserName(String userName);

    List<UserInfoListDTO> listUserInfo();

}
