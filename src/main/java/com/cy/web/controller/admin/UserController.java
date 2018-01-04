package com.cy.web.controller.admin;

import com.cy.common.Response;
import com.cy.common.constant.ResponseStatus;
import com.cy.entity.system.UserInfo;
import com.cy.service.UserInfoService;
import com.cy.web.dto.param.system.RegistParamDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by zxj on 2017/2/17.
 */
@Controller
public class UserController {

    Logger log = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserInfoService userInfoService;

    /**
     * get请求进登录页面，post请求进行登录操作
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Response doLogin(String principal, String credentials) {
        UsernamePasswordToken token = new UsernamePasswordToken(principal, credentials);
        try {
            SecurityUtils.getSubject().login(token);
        } catch (UnknownAccountException e) {
            return new Response(ResponseStatus.USER_NAME_ERROR);
        } catch (IncorrectCredentialsException e) {
            return new Response(ResponseStatus.PASSWORD_ERROR);
        } catch (LockedAccountException e) {
            return new Response(ResponseStatus.ACCOUNT_DENIED);
        } catch (AuthenticationException e) {
            return new Response(ResponseStatus.PARAM_ERROR);
        }
        return new Response();
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
        UserInfo userInfo = userInfoService.regist(paramDTO);
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
        return "redirect:/admin/login";
    }

}
