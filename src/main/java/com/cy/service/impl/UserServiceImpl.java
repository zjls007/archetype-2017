package com.cy.service.impl;

import com.cy.common.emun.ByteBooleanEnum;
import com.cy.common.exception.SystemException;
import com.cy.dao.system.UserInfoDAO;
import com.cy.entity.system.UserInfo;
import com.cy.service.UserService;
import com.cy.web.dto.param.RegistParamDTO;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
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
    public UserInfo regist(RegistParamDTO paramDTO) {
        if (userInfoDAO.selectByUserName(paramDTO.getUserName()) != null) {
            throw new SystemException("用户已存在") ;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(paramDTO.getUserName());
        userInfo.setPassword(paramDTO.getPassword());
        userInfo.setFullName(paramDTO.getFullName());
        userInfo.setTelNo(paramDTO.getTelNo());
        userInfo.setMobileNo(paramDTO.getMobileNo());
        encryptPassword(userInfo);
        Date now = new Date();
        userInfo.setCreateTime(now);
        userInfo.setModifyTime(now);
        userInfo.setAccountLocked(ByteBooleanEnum.FAILED.getCode());
        int result = userInfoDAO.insert(userInfo);
        if (result != 1) {
            throw new RuntimeException("sql影响行数不正确!");
        }
        return userInfo;
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
