package com.cy.web.controller;

import com.cy.common.Response;
import com.cy.common.filter.IFormAuthenticationFilter;
import com.cy.entity.system.UserInfo;
import com.cy.service.UserService;
import com.cy.web.dto.param.RegistParamDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by zxj on 2017/2/17.
 */
@Controller
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    /**
     * get请求进登录页面，post请求进行登录操作
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * get请求
     * @return
     */
    @GetMapping("regist")
    public String regist() {
        return "regist";
    }

    /**
     * post请求
     * @param paramDTO
     * @return
     */
    @PostMapping("regist")
    @ResponseBody
    public Object regist(RegistParamDTO paramDTO) {
        UserInfo userInfo = userService.regist(paramDTO);
        Subject subject = SecurityUtils.getSubject();
        subject.login(new UsernamePasswordToken(userInfo.getUserName(), paramDTO.getPassword()));
        return new Response(userInfo.getId());
    }

    /**
     * 注销操作
     * @return
     */
    @RequestMapping("logout")
    public Object logout() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            SecurityUtils.getSubject().logout();
        }
        return "redirect:/login";
    }

}
