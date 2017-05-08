package com.cy.service;

import com.cy.common.exception.SystemException;
import com.cy.web.dto.param.UserLoginParamDTO;

/**
 * Created by zxj on 2017/5/8.
 */
public interface UserService {

    /**
     * 登录接口
     * @param userLoginDTO
     * @return 用户id
     * @throws {@link SystemException}
     */
    Long login(UserLoginParamDTO userLoginDTO);

}
