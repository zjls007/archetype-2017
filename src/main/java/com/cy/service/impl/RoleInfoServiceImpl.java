package com.cy.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cy.dao.system.RoleInfoDAO;
import com.cy.dao.system.RolePermissionRefDAO;
import com.cy.dao.system.UserRoleRefDAO;
import com.cy.entity.system.RoleInfo;
import com.cy.entity.system.RolePermissionRef;
import com.cy.service.RoleInfoService;
import com.cy.web.dto.param.system.RolePermissionRefSaveDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
        rolePermissionRefDAO.deleteByRoleInfoId(roleInfoId, "menu");
        List<RolePermissionRef> list = new ArrayList<RolePermissionRef>();
        if (menuInfoIdList != null && !menuInfoIdList.isEmpty()) {
            Set<Long> set = new LinkedHashSet<Long>(menuInfoIdList);
            for (Long menuInfoId : set) {
                RolePermissionRef ref = new RolePermissionRef();
                ref.setRoleId(roleInfoId);
                ref.setPermissionId(menuInfoId);
                ref.setType("menu");
                list.add(ref);
            }
            rolePermissionRefDAO.batchInsert(list);
        }
    }

    @Override
    public void saveRefPermissionPage(RolePermissionRefSaveDTO dto) {
        RolePermissionRef ref = new RolePermissionRef();
        BeanUtils.copyProperties(dto, ref);
        ref.setType("page");
        RolePermissionRef rolePermissionRef = rolePermissionRefDAO.getRolePermissionRef(ref.getRoleId(), ref.getPermissionId());
        if (rolePermissionRef == null) {
            rolePermissionRefDAO.insert(ref);
        } else {
            ref.setId(rolePermissionRef.getId());
            rolePermissionRefDAO.update(ref);
        }
    }

}
