package com.cy.web.controller.sys;

import com.cy.common.PageInfo;
import com.cy.common.Response;
import com.cy.dao.system.UserInfoDAO;
import com.cy.dao.system.UserRoleRefDAO;
import com.cy.entity.system.UserInfo;
import com.cy.entity.system.UserRoleRef;
import com.cy.service.UserInfoService;
import com.cy.web.controller.sys.base.DataGridAdaptController;
import com.cy.web.dto.param.system.UserInfoQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zxj on 2017/5/8.
 */
@Controller
@RequestMapping("userInfo")
public class UserInfoController extends DataGridAdaptController<UserInfo, UserInfoQueryDTO> {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private UserRoleRefDAO userRoleRefDAO;

    @Override
    protected List<UserInfo> getData(UserInfoQueryDTO queryDTO) {
        return userInfoDAO.list(queryDTO);
    }

    @Override
    public Response saveOrUpdate(UserInfo userInfo) {
        if (userInfo.getId() == null) {
            userInfoDAO.insert(userInfo);
        } else {
            userInfoDAO.update(userInfo);
        }
        return new Response(userInfo.getId());
    }

    @Override
    protected void doDelete(List<Long> idList) {
        userInfoDAO.batchDelete(idList);
    }

    /**
     * 获取角色页面
     * @param userInfoId
     * @param modelMap
     * @return
     */
    @RequestMapping("refRoleInfo/{userInfoId}")
    public String refRoleInfo(@PathVariable Long userInfoId, ModelMap modelMap) {
        modelMap.put("value", StringUtils.arrayToDelimitedString(userRoleRefDAO.getByUserInfoId(userInfoId).toArray(), ","));
        modelMap.put("userName", userInfoDAO.selectById(userInfoId).getUserName());
        modelMap.put("userInfoId", userInfoId);
        return genPath("refRoleInfo");
    }

    @RequestMapping("saveRefRoleInfo")
    @ResponseBody
    public Response saveRefRoleInfo(Long userInfoId, String values) {
        values = values == null ? "" : values;
        List<Long> roleInfoList = new ArrayList<Long>();
        for (String roleInfoId : values.split(",")) {
            roleInfoList.add(Long.valueOf(roleInfoId));
        }
        userInfoService.saveRefRoleInfo(userInfoId, roleInfoList);
        return new Response(null);
    }

}
