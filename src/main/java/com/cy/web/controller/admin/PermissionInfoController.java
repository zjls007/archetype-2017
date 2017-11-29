package com.cy.web.controller.admin;

import com.cy.common.Response;
import com.cy.dao.system.PermissionInfoDAO;
import com.cy.entity.system.PermissionInfo;
import com.cy.service.PermissionInfoService;
import com.cy.web.controller.admin.base.DataGridAdaptController;
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

    @Autowired
    private PermissionInfoService permissionInfoService;

    @Override
    protected List<PermissionInfo> getData(PermissionInfo queryDTO) {
        return permissionInfoDAO.list(queryDTO);
    }

    @Override
    public Response doSaveOrUpdate(PermissionInfo permissionInfo) {
        permissionInfoService.saveOrUpdate(permissionInfo);
        return new Response(null);
    }

    @Override
    protected void doDelete(List<Long> idList) {
        permissionInfoDAO.batchDelete(idList);
    }

}
