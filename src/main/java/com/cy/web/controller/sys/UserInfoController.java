package com.cy.web.controller.sys;

import com.cy.common.PageInfo;
import com.cy.common.Response;
import com.cy.dao.system.UserInfoDAO;
import com.cy.entity.system.UserInfo;
import com.cy.service.UserInfoService;
import com.cy.web.controller.sys.base.DataGridAdaptController;
import com.cy.web.dto.param.system.UserInfoQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zxj on 2017/5/8.
 */
@Controller
@RequestMapping("userInfo")
public class UserInfoController extends DataGridAdaptController<UserInfo, UserInfoQueryDTO> {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Override
    protected List<UserInfo> getData(UserInfoQueryDTO queryDTO) {
        return userInfoDAO.list();
    }

    @Override
    public Response saveOrUpdate(UserInfo userInfo) {
        if (userInfo.getId() == null) {
            userInfoDAO.insert(userInfo);
        } else {
            userInfoDAO.update(userInfo);
        }
        return new Response(userInfo.getId());
    }

    @Override
    protected void doDelete(List<Long> idList) {
        userInfoDAO.batchDelete(idList);
    }

}
