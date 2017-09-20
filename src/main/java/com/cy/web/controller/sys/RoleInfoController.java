package com.cy.web.controller.sys;

import com.cy.common.PageInfo;
import com.cy.common.Response;
import com.cy.dao.system.RoleInfoDAO;
import com.cy.entity.system.RoleInfo;
import com.cy.service.RoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zxj on 2017/9/15.
 */
@Controller
@RequestMapping("roleInfo")
public class RoleInfoController extends EasyUIAdaptController<RoleInfo> {

    @Autowired
    private RoleInfoDAO roleInfoDAO;

    @Autowired
    private RoleInfoService roleInfoService;

    @Override
    protected List<RoleInfo> getData() {
        return roleInfoDAO.list();
    }

    @Override
    public Response saveOrUpdate(RoleInfo roleInfo) {
        RoleInfo result = roleInfoService.saveOrUpdate(roleInfo);
        return new Response(result);
    }

    @Override
    protected Response doDelete(List<Long> idList) {
        roleInfoDAO.batchDelete(idList);
        return new Response(null);
    }

}
