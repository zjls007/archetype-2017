package com.cy.web.controller.sys;

import com.cy.common.Response;
import com.cy.entity.system.UserGroupInfo;
import com.cy.web.controller.sys.base.TreeGridAdaptController;
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

    @Override
    protected List<UserGroupInfo> getData() {
        return new ArrayList<UserGroupInfo>();
    }

    @Override
    public Response saveOrUpdate(String jsonStr) {
        return null;
    }

}
