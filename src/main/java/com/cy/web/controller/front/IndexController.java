package com.cy.web.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zxj on 2017/11/29.
 */
@Controller
public class IndexController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

}
