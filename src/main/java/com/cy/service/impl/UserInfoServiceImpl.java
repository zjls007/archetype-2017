package com.cy.service.impl;

import com.cy.common.PageInfo;
import com.cy.common.PageResult;
import com.cy.dao.system.UserInfoDAO;
import com.cy.service.UserInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
