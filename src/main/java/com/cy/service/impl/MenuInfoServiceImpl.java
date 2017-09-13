package com.cy.service.impl;

import com.cy.dao.system.MenuInfoDAO;
import com.cy.entity.system.MenuInfo;
import com.cy.service.MenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxj on 2017/9/1.
 */
@Service("menuInfoService")
public class MenuInfoServiceImpl implements MenuInfoService {

    @Autowired
    private MenuInfoDAO menuInfoDAO;

    @Override
    @Transactional
    public void save(List<MenuInfo> list) {
        int i = 0;
        for (MenuInfo item : list) {
            // 设置排序编号
            item.setSortNum(i++);
            Long id = item.getId();
            if (id == null) {
                menuInfoDAO.insert(item);
            } else {
                // 顶级菜单不保存入数据库
                if (id.intValue() != 0) {
                    menuInfoDAO.update(item);
                }
            }
        }
    }

    @Override
    public List<MenuInfo> list() {
        List<MenuInfo> list = new ArrayList<>();
        // 顶级菜单不保存入数据库
        MenuInfo m = new MenuInfo();
        m.setId(0l);
        m.setName("菜单");
        m.setSortNum(0);
        m.setParentId(null);
        list.add(m);
        list.addAll(menuInfoDAO.list());
        return list;
    }
}
