package com.cy.web.controller.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cy.common.Response;
import com.cy.entity.system.MenuInfo;
import com.cy.service.MenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxj on 2017/8/30.
 */
@Controller
@RequestMapping("menuInfo")
public class MenuInfoController {

    @Autowired
    private MenuInfoService menuInfoService;

    @RequestMapping("list")
    public String list() {
        return "menuInfo/list";
    }

    @RequestMapping("data")
    @ResponseBody
    public Object getData() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<MenuInfo> list = menuInfoService.list();
        map.put("total", list.size());
        map.put("rows", list);
        return map;
    }

    @RequestMapping("save")
    @ResponseBody
    public Object save(String jsonStr) {
        List<MenuInfo> list = new ArrayList<MenuInfo>();
        JSONArray jsonArray = JSONArray.parseArray(jsonStr);
        for (int i = 1; i < jsonArray.size(); i++) {
            MenuInfo menuInfo = JSON.toJavaObject(jsonArray.getJSONObject(i), MenuInfo.class);
            if (menuInfo.getId() < 0) {
                menuInfo.setId(null);
            }
            list.add(menuInfo);
        }
        menuInfoService.save(list);
        return new Response("成功");
    }

}
