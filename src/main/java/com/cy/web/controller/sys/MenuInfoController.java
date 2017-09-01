package com.cy.web.controller.sys;

import com.cy.common.PageInfo;
import com.cy.common.util.JsonUtil;
import com.cy.entity.system.MenuInfo;
import com.cy.service.MenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxj on 2017/8/30.
 */
@Controller
@RequestMapping("menuInfo")
public class MenuInfoController extends DataController<MenuInfo> {

    @Autowired
    private MenuInfoService menuInfoService;

    @Override
    public Object data(PageInfo pageInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", "1");
        map.put("rows", menuInfoService.list());
        return map;
    }

}
