package com.cy.service.impl;

import com.cy.dao.UserInfoDAO;
import com.cy.entity.UserInfo;
import com.cy.service.UserInfoService;
import com.cy.util.ValidateUtil;
import com.cy.web.dto.UserLoginDTO;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by zxj on 2017/2/25.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoDAO userInfoDAO;

    public UserInfo login(UserLoginDTO userLoginDTO) {
        ValidateUtil.validate(userLoginDTO);
        return null;
    }

    public Long regeist(UserLoginDTO userLoginDTO) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userLoginDTO.getPrincipal());
        userInfo.setPassword(userLoginDTO.getCredentials());
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

        userInfo.setUserName(username);
        userInfo.setPassword(encodedPassword);
        userInfo.setSalt(salt2);
    }

}
