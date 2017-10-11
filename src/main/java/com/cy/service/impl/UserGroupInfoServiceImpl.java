package com.cy.service.impl;

import com.cy.dao.system.UserGroupInfoDAO;
import com.cy.entity.system.UserGroupInfo;
import com.cy.service.UserGroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxj on 2017/10/11.
 */
@Service("userGroupInfoService")
public class UserGroupInfoServiceImpl implements UserGroupInfoService {

    @Autowired
    private UserGroupInfoDAO userGroupInfoDAO;

    @Override
    public List<UserGroupInfo> list() {
        List<UserGroupInfo> list = new ArrayList<UserGroupInfo>();
        // 顶级菜单不保存入数据库
        UserGroupInfo userGroupInfo = new UserGroupInfo();
        userGroupInfo.setId(0l);
        userGroupInfo.setName("用户组");
        userGroupInfo.setSortNum(0);
        userGroupInfo.setParentId(null);
        list.add(userGroupInfo);
        list.addAll(userGroupInfoDAO.list());
        return list;
    }

    @Override
    public void saveOrUpdate(List<UserGroupInfo> list) {
        userGroupInfoDAO.deleteAll();
        if (list == null || list.isEmpty()) {
            return;
        }
        int i = 0;
        Map<Long, Long> map = new HashMap<>();
        for (UserGroupInfo item : list) {
            // 设置排序编号
            item.setSortNum(i++);
            if (item.getId() > 0) {
                userGroupInfoDAO.insertHasId(item);
            } else {
                long id = item.getId();
                if (item.getParentId() != null && map.containsKey(item.getParentId())) {
                    item.setParentId(map.get(item.getParentId()));
                }
                userGroupInfoDAO.insert(item);
                map.put(id, item.getId());
            }
        }
    }
}
