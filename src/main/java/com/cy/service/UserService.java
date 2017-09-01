package com.cy.service;

import com.cy.entity.system.UserInfo;
import com.cy.web.dto.param.system.RegistParamDTO;

/**
 * Created by zxj on 2017/5/8.
 */
public interface UserService {

    /**
     * 注册
     * @param paramDTO
     * @return 用户id
     * @throws
     */
    UserInfo regist(RegistParamDTO paramDTO);

}
