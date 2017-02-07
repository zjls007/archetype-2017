package com.cy.dao;

import com.cy.entity.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by zxj on 2017/2/7.
 */
@Repository("userInfoDAO")
public interface UserInfoDAO {

    int insert(UserInfo userInfo);

    UserInfo selectById(Long id);

}
