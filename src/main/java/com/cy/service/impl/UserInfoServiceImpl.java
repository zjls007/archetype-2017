package com.cy.service.impl;

import com.cy.common.emun.ByteBooleanEnum;
import com.cy.common.exception.SystemException;
import com.cy.dao.system.UserInfoDAO;
import com.cy.dao.system.UserRoleRefDAO;
import com.cy.entity.system.UserInfo;
import com.cy.entity.system.UserRoleRef;
import com.cy.service.UserInfoService;
import com.cy.web.dto.param.system.RegistParamDTO;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zxj on 2017/2/25.
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoDAO userInfoDAO;

    @Resource
    private UserRoleRefDAO userRoleRefDAO;

    @Override
    @Transactional
    public void saveRefRoleInfo(Long userInfoId, List<Long> roleInfoIdList) {
        userRoleRefDAO.deleteByUserInfoId(userInfoId);
        List<UserRoleRef> list = new ArrayList<UserRoleRef>();
        if (roleInfoIdList != null && !roleInfoIdList.isEmpty()) {
            Set<Long> set = new LinkedHashSet(roleInfoIdList);
            for (Long roleInfoId : set) {
                UserRoleRef ref = new UserRoleRef();
                ref.setUserId(userInfoId);
                ref.setRoleId(roleInfoId);
                list.add(ref);
            }
            userRoleRefDAO.batchInsert(list);
        }
    }

    @Override
    public UserInfo regist(RegistParamDTO paramDTO) {
        if (userInfoDAO.getByUserName(paramDTO.getUserName()) != null) {
            throw new SystemException("用户已存在") ;
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(paramDTO.getUserName());
        userInfo.setPassword(paramDTO.getPassword());
        userInfo.setFullName(paramDTO.getFullName());
        userInfo.setTelNo(paramDTO.getTelNo());
        userInfo.setMobilePhoneNumber(paramDTO.getMobileNo());
        encryptPassword(userInfo);
        Date now = new Date();
        userInfo.setCreateTime(now);
        userInfo.setLstUpdTime(now);
        userInfo.setAccountLocked(ByteBooleanEnum.FAILED.getCode());
        int result = userInfoDAO.insert(userInfo);
        if (result != 1) {
            throw new RuntimeException("sql影响行数不正确!");
        }
        return userInfo;
    }

    @Override
    public void saveOrUpdate(UserInfo userInfo) {
        if (userInfo.getId() == null) {
            // 初始密码为123
            userInfo.setPassword("123");
            encryptPassword(userInfo);
            userInfoDAO.insert(userInfo);
        } else {
            userInfoDAO.updateById(userInfo);
        }
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
