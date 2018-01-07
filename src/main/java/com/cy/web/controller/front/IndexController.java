package com.cy.web.controller.front;

import com.cy.common.Response;
import com.cy.common.constant.*;
import com.cy.common.constant.ResponseStatus;
import com.cy.dao.system.MenuInfoDAO;
import com.cy.dao.system.UserInfoDAO;
import com.cy.entity.system.UserInfo;
import com.cy.service.MenuInfoService;
import com.cy.service.UserInfoService;
import com.cy.web.controller.admin.base.BaseController;
import com.cy.web.dto.param.system.ModifyPwdDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * Created by zxj on 2017/11/29.
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private MenuInfoDAO menuInfoDAO;

    @Autowired
    private MenuInfoService menuInfoService;

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping({"/", "index"})
    public String index(ModelMap modelMap) {
        Subject subject = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo) subject.getSession().getAttribute(Constants.CURRENT_USER);
        modelMap.addAttribute("userInfo", userInfo);

        modelMap.addAttribute("menuInfoList", menuInfoService.list());
        List<Long> menuIdList;
        if (subject.hasRole(Constants.ROLE_SYS_ADMIN) || subject.hasRole(Constants.DEVELOPER)) {
            menuIdList = menuInfoDAO.getAllIdList();
        } else {
            menuIdList = userInfoDAO.getMenuIdList(getCurrentUserId());
        }
        modelMap.addAttribute("hasMenuIdList", menuIdList);
        return "index";
    }

    @RequestMapping({"home"})
    public String home(ModelMap modelMap) {
        return "home";
    }

    @RequestMapping("timeOut")
    public String timeOut() {
        return "timeOut";
    }

    @RequestMapping("exception")
    public String exception() {
        return "exception";
    }

    @GetMapping("modifyPwd")
    public String modifyPwd() {
        return "userInfo/modifyPwd";
    }

    @PostMapping("modifyPwd")
    @ResponseBody
    public Response modifyPwd(ModifyPwdDTO dto) {
        userInfoService.modifyPwd(dto, getCurrentUserId());
        return new Response();
    }

    @GetMapping("test")
    public String test() {
        return "test";
    }

    @RequestMapping("upload")
    @ResponseBody
    public Response fileUpload(@RequestParam("file") CommonsMultipartFile file) {
        System.out.println(file);
        return new Response(System.currentTimeMillis());
    }

}
