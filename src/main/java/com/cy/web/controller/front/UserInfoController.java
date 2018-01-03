package com.cy.web.controller.front;

import com.cy.common.Response;
import com.cy.common.constant.ResponseStatus;
import com.cy.dao.system.UserInfoDAO;
import com.cy.entity.system.UserInfo;
import com.cy.service.UserInfoService;
import com.cy.web.controller.front.base.LayerTableAdaptController;
import com.cy.web.dto.param.system.UserInfoFrontQueryDTO;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Created by hyl on 2017/12/8.
 */
@Controller
@RequestMapping("userInfo")
public class UserInfoController extends LayerTableAdaptController<UserInfo, UserInfoFrontQueryDTO> {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Autowired
    private UserInfoService userInfoService;

    @Override
    protected void doList(ModelMap modelMap) {
        super.doList(modelMap);
        modelMap.addAttribute("modelName", "用户管理");
    }

    @Override
    protected List<? extends UserInfo> getData(UserInfoFrontQueryDTO queryDTO) {
        return userInfoDAO.list(queryDTO);
    }

    @Override
    public Response doSaveOrUpdate(UserInfo userInfo) {
        userInfoService.saveOrUpdate(userInfo);
        return new Response(null);
    }

    @Override
    protected void doDelete(List<Long> idList) {
        userInfoDAO.batchDelete(idList);
    }

    @Override
    protected void doEdit(Long id, ModelMap modelMap) {
        UserInfo userInfo = userInfoDAO.getById(id);
        modelMap.addAttribute("userInfo", userInfo);
    }

    @RequestMapping("changeLockState")
    @ResponseBody
    public Response changeLockState(Long userInfoId, Byte accountLocked) {
        userInfoService.changeLockState(userInfoId, getCurrentUserId(), accountLocked);
        return new Response(null);
    }

    @RequestMapping("uniUserName")
    @ResponseBody
    public Response uniUserName(String userName, Long id) {
        if (userInfoDAO.uniUserName(userName, id) > 0) {
            return new Response(ResponseStatus.VALID_ERROR, "用户名已存在!");
        }
        return new Response(null);
    }

    @RequestMapping("download")
    public void download(HttpServletRequest request,
                         HttpServletResponse response, String type) throws Exception {
        String fileName = "用户导入模板.xls";
        response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Disposition", new String(("filename=" + fileName).getBytes("GBK"), "ISO-8859-1"));

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }


}
