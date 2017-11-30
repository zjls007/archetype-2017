package com.cy.web.controller.front;

import com.cy.dao.system.UserInfoDAO;
import com.cy.entity.system.UserInfo;
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
public class UserInfoController {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @RequestMapping("list")
    public String list() {
        return "userInfo/list";
    }

    @RequestMapping("edit")
    public String edit(Long id, ModelMap modelMap) {
        UserInfo userInfo = userInfoDAO.selectById(id);
        modelMap.addAttribute("userInfo", userInfo);
        return "userInfo/edit";
    }

    @RequestMapping("data")
    @ResponseBody
    public Object data(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit, UserInfoQueryDTO userInfoQueryDTO) {
        PageHelper.startPage(page, limit);
        List<UserInfo> list = userInfoDAO.list(userInfoQueryDTO);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", list.size());
        map.put("data", list);
        return map;
    }

}
