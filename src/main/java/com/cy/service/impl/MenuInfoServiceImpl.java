package com.cy.service.impl;

import com.cy.dao.system.MenuInfoDAO;
import com.cy.entity.system.MenuInfo;
import com.cy.service.MenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        menuInfoDAO.deleteAll();
        if (list == null || list.isEmpty()) {
            return;
        }
        int i = 0;
        Map<Long, Long> map = new HashMap<Long, Long>();
        for (MenuInfo item : list) {
            // 设置排序编号
            item.setSortNum(i++);
            if (item.getId() > 0) {
                menuInfoDAO.insertHasId(item);
            } else {
                long id = item.getId();
                if (item.getParentId() != null && map.containsKey(item.getParentId())) {
                    item.setParentId(map.get(item.getParentId()));
                }
                menuInfoDAO.insert(item);
                map.put(id, item.getId());
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
