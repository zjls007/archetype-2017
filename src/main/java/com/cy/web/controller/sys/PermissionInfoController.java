package com.cy.web.controller.sys;

import com.cy.common.Response;
import com.cy.dao.system.PermissionInfoDAO;
import com.cy.entity.system.PermissionInfo;
import com.cy.web.controller.sys.base.DataGridAdaptController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zxj on 2017/9/20.
 */
@Controller
@RequestMapping("permissionInfo")
public class PermissionInfoController extends DataGridAdaptController<PermissionInfo, PermissionInfo> {

    @Autowired
    private PermissionInfoDAO permissionInfoDAO;

    @Override
    protected List<PermissionInfo> getData(PermissionInfo queryDTO) {
        return permissionInfoDAO.list(queryDTO);
    }

    @Override
    public Response saveOrUpdate(PermissionInfo permissionInfo) {
        if (permissionInfo.getId() == null) {
            permissionInfoDAO.insert(permissionInfo);
        } else {
            permissionInfoDAO.update(permissionInfo);
        }
        return new Response(permissionInfo.getId());
    }

    @Override
    protected void doDelete(List<Long> idList) {
        permissionInfoDAO.batchDelete(idList);
    }

}
