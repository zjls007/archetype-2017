package com.cy.web.controller.sys;

import com.cy.service.MenuInfoService;
import com.cy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zxj on 2017/3/6.
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuInfoService menuInfoService;

    @RequestMapping({"/", "/index"})
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("menuInfoList", menuInfoService.list());
        return "index";
    }

}
