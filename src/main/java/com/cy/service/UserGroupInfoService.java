package com.cy.service;

import com.cy.entity.system.UserGroupInfo;

import java.util.List;

/**
 * Created by zxj on 2017/10/11.
 */
public interface UserGroupInfoService {

    void saveOrUpdate(List<UserGroupInfo> list);

    List<UserGroupInfo> list();

}
