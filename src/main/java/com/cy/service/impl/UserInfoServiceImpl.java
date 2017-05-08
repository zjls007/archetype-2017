package com.cy.service.impl;

import com.cy.common.PageInfo;
import com.cy.common.PageResult;
import com.cy.common.util.ValidateUtil;
import com.cy.dao.UserInfoDAO;
import com.cy.entity.UserInfo;
import com.cy.service.UserInfoService;
import com.cy.web.dto.param.UserLoginParamDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by zxj on 2017/2/25.
 */
@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoDAO userInfoDAO;

    @Override
    public PageResult data(PageInfo pageInfo) {
        PageHelper.startPage(pageInfo.getPage().intValue(), pageInfo.getRows().intValue());
        Page page = (Page) userInfoDAO.listUserInfo();
        return new PageResult(page);
    }
}
