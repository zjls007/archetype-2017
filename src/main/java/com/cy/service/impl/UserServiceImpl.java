package com.cy.service.impl;

import com.cy.controller.dto.UserLoginDTO;
import com.cy.core.PasswordUtil;
import com.cy.dao.UserInfoDAO;
import com.cy.entity.UserInfo;
import com.cy.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zxj on 2017/2/25.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserInfoDAO userInfoDAO;

    public void regeist(UserLoginDTO userLoginDTO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userLoginDTO.getPrincipal());
        userInfo.setPassword(userLoginDTO.getCredentials());
        encryptPassword(userInfo);
        userInfoDAO.insert(userInfo);
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

        userInfo.setUserName(username);
        userInfo.setPassword(encodedPassword);
        userInfo.setSalt(salt2);
    }
}
