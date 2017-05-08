package com.cy.web.controller;

import com.cy.common.PageInfo;
import com.cy.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zxj on 2017/5/8.
 */
@Controller
@RequestMapping("userInfo")
public class UserInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("list")
    public String list() {
        return "userInfo/list";
    }

    @RequestMapping("data")
    @ResponseBody
    public Object data(PageInfo pageInfo) {
        return userInfoService.data(pageInfo);
    }

}
