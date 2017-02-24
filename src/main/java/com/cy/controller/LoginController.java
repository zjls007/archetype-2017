package com.cy.controller;

import com.cy.controller.dto.UserLoginDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zxj on 2017/2/17.
 */
@RestController
public class LoginController {

    Logger log = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/login")
    public Object login(UserLoginDTO userLoginDTO) {
        String principal = userLoginDTO.getPrincipal();
        String credentials = userLoginDTO.getCredentials();

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

        return "a";
    }

}
