package com.cy.web.controller.front;

import com.cy.common.Response;
import com.cy.dao.system.UserInfoDAO;
import com.cy.entity.system.UserInfo;
import com.cy.service.UserInfoService;
import com.cy.web.controller.front.base.LayerTableAdaptController;
import com.cy.web.dto.param.system.UserInfoFrontQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hyl on 2017/12/8.
 */
@Controller
@RequestMapping("userInfo")
public class UserInfoController extends LayerTableAdaptController<UserInfo, UserInfoFrontQueryDTO> {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    protected List<? extends UserInfo> getData(UserInfoFrontQueryDTO queryDTO) {
        return userInfoDAO.list(queryDTO);
    }

    @Override
    public Response doSaveOrUpdate(UserInfo userInfo) {
        userInfoService.saveOrUpdate(userInfo);
        return new Response(null);
    }

    @Override
    protected void doDelete(List<Long> idList) {
        userInfoDAO.batchDelete(idList);
    }

    @Override
    protected void doEdit(Long id, ModelMap modelMap) {
        UserInfo userInfo = userInfoDAO.getById(id);
        modelMap.addAttribute("userInfo", userInfo);
    }

    @RequestMapping("changeLockState")
    @ResponseBody
    public Response changeLockState(Long userInfoId, Byte accountLocked) {
        userInfoService.changeLockState(userInfoId, getCurrentUserId(), accountLocked);
        return new Response(null);
    }

}
