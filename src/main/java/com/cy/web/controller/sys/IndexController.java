package com.cy.web.controller.sys;

import com.cy.common.constant.Constants;
import com.cy.dao.system.MenuInfoDAO;
import com.cy.dao.system.UserInfoDAO;
import com.cy.service.MenuInfoService;
import com.cy.web.controller.sys.base.BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zxj on 2017/3/6.
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private MenuInfoDAO menuInfoDAO;

    @Autowired
    private MenuInfoService menuInfoService;

    @RequestMapping({"/", "/index"})
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("menuInfoList", menuInfoService.list());
        List<Long> menuIdList;
        Subject subject = getSubject();
        if (subject.hasRole(Constants.ROLE_SYS_ADMIN) || subject.hasRole(Constants.DEVELOPER)) {
            menuIdList = menuInfoDAO.getAllIdList();
        } else {
            menuIdList = userInfoDAO.getMenuIdList(getCurrentUserId());
        }
        modelMap.addAttribute("hasMenuIdList", menuIdList);
        return "index";
    }

}
