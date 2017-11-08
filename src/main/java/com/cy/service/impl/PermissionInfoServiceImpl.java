package com.cy.service.impl;

import com.cy.dao.system.PermissionInfoDAO;
import com.cy.dao.system.RolePermissionRefDAO;
import com.cy.entity.system.PermissionInfo;
import com.cy.service.PermissionInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zxj on 2017/11/8.
 */
@Service("permissionInfoService")
public class PermissionInfoServiceImpl implements PermissionInfoService {

    @Autowired
    private PermissionInfoDAO permissionInfoDAO;

    @Autowired
    private RolePermissionRefDAO rolePermissionRefDAO;

    @Override
    public void saveOrUpdate(PermissionInfo permissionInfo) {
        Long id = permissionInfo.getId();
        if (id == null) {
            permissionInfoDAO.insert(permissionInfo);
        } else {
            rolePermissionRefDAO.updatePermName(id, permissionInfo.getName(), permissionInfo.getCode());
            permissionInfoDAO.update(permissionInfo);
        }
    }

}
