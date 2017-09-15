package com.cy.web.controller.sys;

import com.cy.common.PageInfo;
import com.cy.entity.system.RoleInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zxj on 2017/9/15.
 */
@Controller
@RequestMapping("roleInfo")
public class RoleInfoController extends DataController<RoleInfo> {

    @Override
    public Object data(PageInfo pageInfo) {
        return null;
    }


}
