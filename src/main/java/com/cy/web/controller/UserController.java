package com.cy.web.controller;

import com.cy.common.Response;
import com.cy.service.UserService;
import com.cy.web.dto.param.UserLoginParamDTO;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zxj on 2017/2/17.
 */
@RestController
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @RequestMapping("/register")
    public Object register(UserLoginParamDTO userLoginDTO) {
//        userService.regeist(userLoginDTO);
        return null;
    }


    @RequestMapping("/login")
    @ResponseBody
    public Response login(UserLoginParamDTO userLoginDTO) {
        return new Response(userService.login(userLoginDTO));
    }


    @RequestMapping("/logout")
    public Object logout() {
//        SecurityUtils.getSubject().isAuthenticated()
        SecurityUtils.getSubject().logout();
        return null;
    }

}
