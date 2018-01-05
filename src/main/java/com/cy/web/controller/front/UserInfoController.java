package com.cy.web.controller.front;

import com.cy.common.Response;
import com.cy.dao.system.UserInfoDAO;
import com.cy.entity.system.UserInfo;
import com.cy.service.UserInfoService;
import com.cy.web.controller.front.base.LayerTableAdaptController;
import com.cy.web.dto.param.system.UserInfoFrontQueryDTO;
import com.cy.web.dto.result.system.UserInfoListResultDTO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String getModelNameCN() {
        return "用户";
    }

    @Override
    protected List<? extends UserInfo> getData(UserInfoFrontQueryDTO queryDTO) {
        return userInfoDAO.list(queryDTO);
    }

    @Override
    public Response doSaveOrUpdate(UserInfo userInfo) {
        userInfoService.saveOrUpdate(userInfo);
        return new Response();
    }

    @Override
    protected void doDelete(List<Long> idList) {
        userInfoDAO.batchDelete(idList);
    }

    @Override
    protected UserInfo getModel(Long id) {
        return userInfoDAO.getById(id);
    }

    @RequestMapping("changeLockState")
    @ResponseBody
    public Response changeLockState(Long userInfoId, Byte accountLocked) {
        userInfoService.changeLockState(userInfoId, getCurrentUserId(), accountLocked);
        return new Response();
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

    @RequestMapping("getUserList")
    @ResponseBody
    public Response getUserList(Integer pageSize, Integer curPage, String q) {
        PageHelper.startPage(curPage, pageSize);
        UserInfoFrontQueryDTO query = new UserInfoFrontQueryDTO();
        query.setUserName(q);
        List<UserInfo> list = userInfoDAO.list(query);
        List<Map<String, String>> result = new ArrayList<>();
        for (UserInfo item : list) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("id", Long.toString(item.getId()));
            map.put("text", item.getUserName());
            result.add(map);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", result);
        map.put("hasNextPage", ((Page)list).getTotal() >= curPage*pageSize);
        return new Response(map);
    }


}
