package com.cy.web.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cy.common.Response;
import com.cy.dao.system.RoleInfoDAO;
import com.cy.dao.system.UserInfoDAO;
import com.cy.entity.system.RoleInfo;
import com.cy.service.RoleInfoService;
import com.cy.web.controller.sys.base.DataGridAdaptController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private UserInfoDAO userInfoDAO;

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

    /**
     * 获取权限页面
     * @param userInfoId
     * @param modelMap
     * @return
     */
    @RequestMapping("refPermission/{userInfoId}")
    public String refRoleInfo(@PathVariable Long userInfoId, ModelMap modelMap) {
        modelMap.put("userName", userInfoDAO.selectById(userInfoId).getUserName());
        modelMap.put("userInfoId", userInfoId);
        return genPath("refPermission");
    }

}
