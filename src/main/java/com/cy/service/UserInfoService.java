package com.cy.service;

import com.cy.common.PageInfo;
import com.cy.common.PageResult;

import java.util.List;

/**
 * Created by zxj on 2017/2/25.
 */
public interface UserInfoService {

    void saveRefRoleInfo(Long userInfoId, List<Long> roleInfoIdList);

}
