package com.cy.web.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cy.common.Response;
import com.cy.entity.system.MenuInfo;
import com.cy.service.MenuInfoService;
import com.cy.web.controller.admin.base.TreeGridAdaptController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxj on 2017/8/30.
 */
@Controller
@RequestMapping("menuInfo")
public class MenuInfoController extends TreeGridAdaptController<MenuInfo> {

    @Autowired
    private MenuInfoService menuInfoService;

    @Override
    protected List<MenuInfo> getData() {
        return menuInfoService.list();
    }

    @Override
    public Response saveOrUpdate(String jsonStr) {
        List<MenuInfo> list = new ArrayList<MenuInfo>();
        JSONArray jsonArray = JSONArray.parseArray(jsonStr);
        for (int i = 1; i < jsonArray.size(); i++) {
            MenuInfo menuInfo = JSON.toJavaObject(jsonArray.getJSONObject(i), MenuInfo.class);
            list.add(menuInfo);
        }
        menuInfoService.saveOrUpdate(list);
        return new Response(null);
    }

}
