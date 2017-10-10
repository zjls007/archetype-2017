package com.cy.service;

import com.cy.entity.system.RoleInfo;

/**
 * Created by zxj on 2017/9/19.
 */
public interface RoleInfoService {

    RoleInfo saveOrUpdate(RoleInfo roleInfo);

    Object userRefRoleInfoData(Long userInfoId);

}
