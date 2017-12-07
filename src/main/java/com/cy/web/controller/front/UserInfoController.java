package com.cy.web.controller.front;

import com.cy.common.Response;
import com.cy.dao.system.UserInfoDAO;
import com.cy.entity.system.UserInfo;
import com.cy.service.UserInfoService;
import com.cy.web.controller.admin.base.BaseController;
import com.cy.web.dto.param.system.UserInfoFrontQueryDTO;
import com.cy.web.dto.param.system.UserInfoQueryDTO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxj on 2017/11/30.
 */
@Controller
@RequestMapping("userInfo")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("list")
    public String list() {
        return "userInfo/list";
    }

    @RequestMapping("edit")
    public String edit(Long id, ModelMap modelMap) {
        UserInfo userInfo = userInfoDAO.getById(id);
        modelMap.addAttribute("userInfo", userInfo);
        return "userInfo/edit";
    }

    @RequestMapping("data")
    @ResponseBody
    public Object data(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, UserInfoFrontQueryDTO queryDTO) {
        PageHelper.startPage(page, limit);
        List<UserInfo> list = userInfoDAO.list(queryDTO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);
        return map;
    }

    @RequestMapping("changeLockState")
    @ResponseBody
    public Response changeLockState(Long userInfoId, Byte accountLocked) {
        userInfoService.changeLockState(userInfoId, getCurrentUserId(), accountLocked);
        return new Response(null);
    }

}
