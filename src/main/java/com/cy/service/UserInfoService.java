package com.cy.service;

import com.cy.common.PageInfo;
import com.cy.common.PageResult;
import com.cy.entity.system.UserInfo;
import com.cy.web.dto.param.system.RegistParamDTO;

import java.util.List;

/**
 * Created by zxj on 2017/2/25.
 */
public interface UserInfoService {

    void saveRefRoleInfo(Long userInfoId, List<Long> roleInfoIdList);

    void saveOrUpdate(UserInfo userInfo);

    /**
     * 注册
     * @param paramDTO
     * @return 用户id
     * @throws
     */
    UserInfo regist(RegistParamDTO paramDTO);

}
