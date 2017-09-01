package com.cy.service;

import com.cy.entity.system.MenuInfo;

import java.util.List;

/**
 * Created by zxj on 2017/9/1.
 */
public interface MenuInfoService {

    void save(List<MenuInfo> list);

    List<MenuInfo> list();

}
