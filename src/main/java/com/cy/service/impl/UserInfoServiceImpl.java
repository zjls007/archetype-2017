package com.cy.service.impl;

import com.cy.common.PageInfo;
import com.cy.common.PageResult;
import com.cy.dao.system.UserInfoDAO;
import com.cy.dao.system.UserRoleRefDAO;
import com.cy.entity.system.UserRoleRef;
import com.cy.service.UserInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

}
