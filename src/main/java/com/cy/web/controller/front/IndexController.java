package com.cy.web.controller.front;

import com.cy.common.constant.Constants;
import com.cy.entity.system.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zxj on 2017/11/29.
 */
@Controller
public class IndexController {

    @RequestMapping({"/", "/index"})
    public String index(ModelMap modelMap) {
        Subject subject = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo) subject.getSession().getAttribute(Constants.CURRENT_USER);
        modelMap.addAttribute("userInfo", userInfo);
        return "/index";
    }

}
