package com.cy.web.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cy.common.Response;
import com.cy.dao.system.PermissionInfoDAO;
import com.cy.dao.system.RoleInfoDAO;
import com.cy.dao.system.RolePermissionRefDAO;
import com.cy.dao.system.UserInfoDAO;
import com.cy.entity.system.PermissionInfo;
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

import java.util.ArrayList;
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
    private RolePermissionRefDAO rolePermissionRefDAO;

    @Autowired
    private PermissionInfoDAO permissionInfoDAO;

    @Override
    protected List<RoleInfo> getData(RoleInfo queryDTO) {
        return roleInfoDAO.list(queryDTO);
    }

    @Override
    public Response doSaveOrUpdate(RoleInfo roleInfo) {
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
     * 菜单权限
     * @param roleInfoId
     * @param modelMap
     * @return
     */
    @RequestMapping("refPermissionMenu/{roleInfoId}")
    public String refPermissionMenu(@PathVariable Long roleInfoId, ModelMap modelMap) {
        modelMap.put("values", StringUtils.arrayToDelimitedString(rolePermissionRefDAO.getMenuByRoleInfoId(roleInfoId).toArray(), ","));
        modelMap.put("roleInfoId", roleInfoId);
        return genPath("refPermissionMenu");
    }

    @RequestMapping("saveRefPermissionMenu")
    @ResponseBody
    public Response saveRefPermissionMenu(Long roleInfoId, String values) {
        values = values == null ? "" : values;
        List<Long> menuInfoIdList = new ArrayList<Long>();
        for (String menuInfoId : values.split(",")) {
            if (!"".equals(menuInfoId)) {
                menuInfoIdList.add(Long.valueOf(menuInfoId));
            }
        }
        roleInfoService.saveRefPermissionMenu(roleInfoId, menuInfoIdList);
        return new Response(null);
    }

    /**
     * 页面权限
     * @param roleInfoId
     * @param modelMap
     * @return
     */
    @RequestMapping("refPermissionPage/{roleInfoId}")
    public String refPermissionPage(@PathVariable Long roleInfoId, ModelMap modelMap) {
        modelMap.put("roleInfoId", roleInfoId);
        return genPath("refPermissionPage");
    }

    @RequestMapping("getPermissionData")
    @ResponseBody
    public Object getPermissionData() {
        List<PermissionInfo> list = permissionInfoDAO.list(null);
        JSONArray jsonArray = new JSONArray();
        for (PermissionInfo item : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", item.getId());
            jsonObject.put("text", item.getName());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

}
