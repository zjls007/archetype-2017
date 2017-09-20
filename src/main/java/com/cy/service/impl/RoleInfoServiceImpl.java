package com.cy.service.impl;

import com.cy.dao.system.RoleInfoDAO;
import com.cy.entity.system.RoleInfo;
import com.cy.service.RoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zxj on 2017/9/19.
 */
@Service("roleInfoService")
public class RoleInfoServiceImpl implements RoleInfoService {

    @Autowired
    private RoleInfoDAO roleInfoDAO;

    @Override
    public RoleInfo saveOrUpdate(RoleInfo roleInfo) {
        Long id = roleInfo.getId();
        if (id == null) {
            roleInfoDAO.insert(roleInfo);
        } else {
            roleInfoDAO.update(roleInfo);
        }
        return roleInfo;
    }

}
