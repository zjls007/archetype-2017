package com.cy.common.util;

import com.cy.entity.system.UserInfo;
import org.springframework.util.StringUtils;

/**
 * Created by zxj on 2018/2/1.
 */
public class UserNameUtil {

    public static String getUserName(UserInfo userInfo) {
        return StringUtils.isEmpty(userInfo.getFullName()) ? userInfo.getUserName() : userInfo.getFullName();
    }

}
