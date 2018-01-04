package com.cy.web.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cy.common.Response;
import com.cy.entity.system.UserGroupInfo;
import com.cy.service.UserGroupInfoService;
import com.cy.web.controller.admin.base.TreeGridAdaptController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxj on 2017/10/11.
 */
@RequestMapping("userGroupInfo")
@Controller
public class UserGroupInfoController extends TreeGridAdaptController<UserGroupInfo> {

    @Autowired
    private UserGroupInfoService userGroupInfoService;

    @Override
    protected List<UserGroupInfo> getData() {
        return userGroupInfoService.list();
    }

    @Override
    public Response saveOrUpdate(String jsonStr) {
        List<UserGroupInfo> list = new ArrayList<UserGroupInfo>();
        JSONArray jsonArray = JSONArray.parseArray(jsonStr);
        for (int i = 1; i < jsonArray.size(); i++) {
            UserGroupInfo userGroupInfo = JSON.toJavaObject(jsonArray.getJSONObject(i), UserGroupInfo.class);
            list.add(userGroupInfo);
        }
        userGroupInfoService.saveOrUpdate(list);
        return new Response();
    }

}
