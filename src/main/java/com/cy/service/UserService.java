package com.cy.service;

import com.cy.entity.UserInfo;
import com.cy.web.dto.UserLoginDTO;

/**
 * Created by zxj on 2017/2/25.
 */
public interface UserService {

    UserInfo login(UserLoginDTO userLoginDTO);

    void regeist(UserLoginDTO userLoginDTO);

}
