package com.cy.web.controller.front;

import com.cy.common.Response;
import com.cy.common.constant.ResponseStatus;
import com.cy.dao.system.UserInfoDAO;
import com.cy.entity.system.UserInfo;
import com.cy.service.UserInfoService;
import com.cy.web.controller.admin.base.BaseController;
import com.cy.web.dto.param.system.UserInfoFrontQueryDTO;
import com.cy.web.dto.param.system.UserInfoQueryDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxj on 2017/11/30.
 */
//@Controller
//@RequestMapping("userInfo")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("list")
    public String list() {
        return "userInfo/list";
    }

    @RequestMapping("data")
    @ResponseBody
    public Object data(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, UserInfoFrontQueryDTO queryDTO) {
        PageHelper.startPage(page, limit);
        List<UserInfo> list = userInfoDAO.list(queryDTO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", ((Page) list).getTotal());
        map.put("data", list);
        return map;
    }

    @RequestMapping("changeLockState")
    @ResponseBody
    public Response changeLockState(Long userInfoId, Byte accountLocked) {
        userInfoService.changeLockState(userInfoId, getCurrentUserId(), accountLocked);
        return new Response(null);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Response delete(@RequestBody List<Long> idList) {
        userInfoDAO.batchDelete(idList);
        return new Response(null);
    }

    @RequestMapping({"edit/{id}", "edit"})
    public String edit(@PathVariable(required=false) Long id, ModelMap modelMap) {
        UserInfo userInfo = userInfoDAO.getById(id);
        modelMap.addAttribute("userInfo", userInfo);
        return "userInfo/edit";
    }

}
