package com.cy.service.impl;

import com.cy.dao.UserInfoDAO;
import com.cy.entity.UserInfo;
import com.cy.service.UserService;
import com.cy.util.ValidateUtil;
import com.cy.web.dto.UserLoginDTO;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Set;

/**
 * Created by zxj on 2017/2/25.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserInfoDAO userInfoDAO;

    public UserInfo login(UserLoginDTO userLoginDTO) {
        ValidateUtil.validate(userLoginDTO);
        return null;
    }

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

    public void a() {
        PageHelper.startPage(1, 1);
        userInfoDAO.selectAll();
    }
}
