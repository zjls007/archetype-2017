package com.cy.service.impl;

import com.cy.common.constant.Constants;
import com.cy.common.exception.SystemException;
import com.cy.dao.UserInfoDAO;
import com.cy.entity.UserInfo;
import com.cy.service.UserService;
import com.cy.web.dto.param.RegistParamDTO;
import com.cy.web.dto.param.UserLoginParamDTO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by zxj on 2017/5/8.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDAO userInfoDAO;

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

    @Override
    public Long regist(RegistParamDTO paramDTO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(paramDTO.getUserName());
        userInfo.setPassword(paramDTO.getPassword());
        encryptPassword(userInfo);
        Date now = new Date();
        userInfo.setCreateTime(now);
        userInfo.setModifyTime(now);
        int result = userInfoDAO.insert(userInfo);
        if (result != 1) {
            throw new RuntimeException("sql影响行数不正确!");
        }
        return userInfo.getId();
    }

    private void encryptPassword(UserInfo userInfo) {
        String algorithmName = "md5";
        String username = userInfo.getUserName();
        String password = userInfo.getPassword();
        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;

        SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
        String encodedPassword = hash.toHex();

        userInfo.setPassword(encodedPassword);
        userInfo.setSalt(salt2);
    }

}
