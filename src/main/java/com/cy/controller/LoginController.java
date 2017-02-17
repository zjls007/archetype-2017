package com.cy.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zxj on 2017/2/17.
 */
@Controller
public class LoginController {

    Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    public ModelAndView login(ModelAndView mav) {
        String principal = "a";
        String credentials = "1";

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(principal, credentials);
//        token.setRememberMe(true);
        try {
            currentUser.login(token);
        } catch (UnknownAccountException uae) {
            log.info("账户{}不存在!", principal);
        } catch (IncorrectCredentialsException ice) {
            log.info("账户{}密码不正确!", principal);
        } catch (LockedAccountException lae) {
            log.info("账户{}已被锁定!", principal);
        }
        // ... catch more exceptions here (maybe custom ones specific to your application?
        catch (AuthenticationException ae) {
            log.info("登录异常!");
        }

        mav.setViewName("index");
        return mav;
    }

}
