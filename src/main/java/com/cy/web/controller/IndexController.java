package com.cy.web.controller;

import com.cy.common.util.ApplicationContextHelper;
import org.springframework.stereotype.Controller;
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
    public String index() {
        return "index";
    }
}
