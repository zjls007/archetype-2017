package com.cy.web.controller.sys;

import com.cy.dao.system.UserInfoDAO;
import com.cy.service.MenuInfoService;
import com.cy.service.UserService;
import com.cy.web.controller.sys.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zxj on 2017/3/6.
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private MenuInfoService menuInfoService;

    @RequestMapping({"/", "/index"})
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("menuInfoList", menuInfoService.list());
        modelMap.addAttribute("hasMenuIdList", userInfoDAO.getMenuIdList(getCurrentUserId()));
        return "index";
    }

}
