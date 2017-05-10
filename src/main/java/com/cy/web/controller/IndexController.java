package com.cy.web.controller;

import com.cy.common.util.ApplicationContextHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.Set;

/**
 * Created by zxj on 2017/3/6.
 */
@Controller
public class IndexController {

    @RequestMapping({"/", "/index"})
    public String index(ModelMap modelMap) {
        modelMap.put("frameUrl", "<iframe src=\"userInfo/list\" allowTransparency=\"true\" style=\"border:0;width:100%;height:99%;\" frameBorder=\"0\"></iframe>");
        return "index";
    }
}
