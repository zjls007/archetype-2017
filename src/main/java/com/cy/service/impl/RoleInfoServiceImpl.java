package com.cy.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cy.dao.system.RoleInfoDAO;
import com.cy.dao.system.RolePermissionRefDAO;
import com.cy.dao.system.UserRoleRefDAO;
import com.cy.entity.system.RoleInfo;
import com.cy.service.RoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zxj on 2017/9/19.
 */
@Service("roleInfoService")
public class RoleInfoServiceImpl implements RoleInfoService {

    @Autowired
    private RoleInfoDAO roleInfoDAO;

    @Autowired
    private UserRoleRefDAO userRoleRefDAO;

    @Autowired
    private RolePermissionRefDAO rolePermissionRefDAO;

    @Override
    public RoleInfo saveOrUpdate(RoleInfo roleInfo) {
        Long id = roleInfo.getId();
        if (id == null) {
            roleInfoDAO.insert(roleInfo);
        } else {
            roleInfoDAO.update(roleInfo);
        }
        return roleInfo;
    }

    @Override
    public Object userRefRoleInfoData(Long userInfoId) {
        List<RoleInfo> list = roleInfoDAO.list(new RoleInfo());
        JSONArray jsonArray = new JSONArray();
        for (RoleInfo item : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", item.getId());
            jsonObject.put("text", item.getName());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public void saveRefPermissionMenu(Long roleInfoId, List<Long> menuInfoIdList) {
//        userRoleRefDAO.deleteByUserInfoId(userInfoId);
//        List<UserRoleRef> list = new ArrayList<UserRoleRef>();
//        if (roleInfoIdList != null && !roleInfoIdList.isEmpty()) {
//            Set<Long> set = new LinkedHashSet(roleInfoIdList);
//            for (Long roleInfoId : set) {
//                UserRoleRef ref = new UserRoleRef();
//                ref.setUserId(userInfoId);
//                ref.setRoleId(roleInfoId);
//                list.add(ref);
//            }
//            userRoleRefDAO.batchInsert(list);
//        }
    }

}
