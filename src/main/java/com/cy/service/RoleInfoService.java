package com.cy.service;

import com.cy.entity.system.RoleInfo;

import java.util.List;

/**
 * Created by zxj on 2017/9/19.
 */
public interface RoleInfoService {

    RoleInfo saveOrUpdate(RoleInfo roleInfo);

    Object userRefRoleInfoData(Long userInfoId);

    void saveRefPermissionMenu(Long roleInfoId, List<Long> menuInfoIdList);

}
