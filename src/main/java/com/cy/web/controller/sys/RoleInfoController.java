package com.cy.web.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cy.common.Response;
import com.cy.dao.system.RoleInfoDAO;
import com.cy.entity.system.RoleInfo;
import com.cy.service.RoleInfoService;
import com.cy.web.controller.sys.base.DataGridAdaptController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zxj on 2017/9/15.
 */
@Controller
@RequestMapping("roleInfo")
public class RoleInfoController extends DataGridAdaptController<RoleInfo, RoleInfo> {

    @Autowired
    private RoleInfoDAO roleInfoDAO;

    @Autowired
    private RoleInfoService roleInfoService;

    @Override
    protected List<RoleInfo> getData(RoleInfo queryDTO) {
        return roleInfoDAO.list(queryDTO);
    }

    @Override
    public Response saveOrUpdate(RoleInfo roleInfo) {
        RoleInfo result = roleInfoService.saveOrUpdate(roleInfo);
        return new Response(result);
    }

    @Override
    protected void doDelete(List<Long> idList) {
        roleInfoDAO.batchDelete(idList);
    }

    @RequestMapping("userRefRoleInfoData")
    @ResponseBody
    public Object userRefRoleInfoData(Long userInfoId) {
        return roleInfoService.userRefRoleInfoData(userInfoId);
    }

}
