package com.cy.web.controller;

import com.cy.common.Constants;
import com.cy.entity.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * Created by zxj on 2017/5/8.
 */
public class BaseController {

    public UserInfo getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo) subject.getSession().getAttribute(Constants.CURRENT_USER);
        return userInfo;
    }

    public Long getCurrentUserId() {
        Subject subject = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo) subject.getSession().getAttribute(Constants.CURRENT_USER);
        return userInfo.getId();
    }

}
