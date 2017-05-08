package com.cy.service.impl;

import com.cy.common.Constants;
import com.cy.common.exception.SystemException;
import com.cy.service.UserService;
import com.cy.web.dto.param.UserLoginParamDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * Created by zxj on 2017/5/8.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public Long login(UserLoginParamDTO userLoginDTO) {
        String principal = userLoginDTO.getPrincipal();
        String credentials = userLoginDTO.getCredentials();

        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(principal, credentials);
//        token.setRememberMe(true);
        try {
            currentUser.login(token);
        } catch (UnknownAccountException e) {
            throw new SystemException("账号不存在", e) ;
        } catch (IncorrectCredentialsException e) {
            throw new SystemException("密码不正确", e) ;
        } catch (LockedAccountException e) {
            throw new SystemException("账号被锁定", e) ;
        }
        return (Long) currentUser.getSession().getAttribute(Constants.CURRENT_USER_ID);
    }

}
