package com.cy.service;

import com.cy.common.PageInfo;
import com.cy.common.PageResult;
import com.cy.entity.UserInfo;
import com.cy.web.dto.UserLoginDTO;

/**
 * Created by zxj on 2017/2/25.
 */
public interface UserInfoService {

    UserInfo login(UserLoginDTO userLoginDTO);

    Long regeist(UserLoginDTO userLoginDTO);

    PageResult data(PageInfo pageInfo);

}
