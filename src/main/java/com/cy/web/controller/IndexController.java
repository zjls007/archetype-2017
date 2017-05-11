package com.cy.web.controller;

import com.cy.common.Response;
import com.cy.common.util.ApplicationContextHelper;
import com.cy.service.UserService;
import com.cy.web.dto.param.RegistParamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Autowired
    private UserService userService;

    @RequestMapping({"/", "/index"})
    public String index(ModelMap modelMap) {
        return "index";
    }

    @RequestMapping("regist")
    @ResponseBody
    public Object regist(RegistParamDTO paramDTO) {
        return new Response(userService.regist(paramDTO));
    }

}
