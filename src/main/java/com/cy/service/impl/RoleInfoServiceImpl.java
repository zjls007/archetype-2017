package com.cy.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cy.dao.system.MenuInfoDAO;
import com.cy.dao.system.RoleInfoDAO;
import com.cy.dao.system.RolePermissionRefDAO;
import com.cy.entity.system.MenuInfo;
import com.cy.entity.system.RoleInfo;
import com.cy.entity.system.RolePermissionRef;
import com.cy.entity.system.emun.RolePermissionRefType;
import com.cy.service.RoleInfoService;
import com.cy.web.dto.param.system.RolePermissionRefSaveDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zxj on 2017/9/19.
 */
@Service("roleInfoService")
public class RoleInfoServiceImpl implements RoleInfoService {

    @Autowired
    private RoleInfoDAO roleInfoDAO;

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
        rolePermissionRefDAO.deleteByRoleInfoId(roleInfoId, RolePermissionRefType.MENU.getCode());
        List<RolePermissionRef> list = new ArrayList<RolePermissionRef>();
        menuInfoIdList.add(0l);
        menuInfoIdList = getAllLevelId(menuInfoIdList);
        if (menuInfoIdList != null && !menuInfoIdList.isEmpty()) {
            Set<Long> set = new LinkedHashSet<Long>(menuInfoIdList);
            for (Long menuInfoId : set) {
                RolePermissionRef ref = new RolePermissionRef();
                ref.setRoleId(roleInfoId);
                ref.setPermissionId(menuInfoId);
                ref.setType(RolePermissionRefType.MENU.getCode());
                list.add(ref);
            }
            rolePermissionRefDAO.batchInsert(list);
        }
    }

    @Autowired
    private MenuInfoDAO menuInfoDAO;

    private List<Long> getAllLevelId(List<Long> menuInfoIdList) {
        Set<Long> set = new HashSet<Long>();
        for (Long id : menuInfoIdList) {
            set.add(id);
            if (id.intValue() == 0) {
                continue;
            }
            boolean cont = true;
            do {
                MenuInfo menuInfo = menuInfoDAO.getById(id);
                menuInfo = menuInfoDAO.getById(menuInfo.getParentId());
                if (menuInfo != null) {
                    id = menuInfo.getId();
                    set.add(id);
                } else {
                    cont = false;
                }
            } while (cont);
        }
        return new ArrayList<Long>(set);
    }

    @Override
    public void saveRefPermissionPage(RolePermissionRefSaveDTO dto) {
        RolePermissionRef ref = new RolePermissionRef();
        BeanUtils.copyProperties(dto, ref);
        ref.setType(RolePermissionRefType.PAGE.getCode());
        RolePermissionRef rolePermissionRef = rolePermissionRefDAO.getRolePermissionRef(ref.getRoleId(), ref.getPermissionId(), RolePermissionRefType.PAGE.getCode());
        if (rolePermissionRef == null) {
            rolePermissionRefDAO.insert(ref);
        } else {
            ref.setId(rolePermissionRef.getId());
            rolePermissionRefDAO.update(ref);
        }
    }

}
