package com.cy.web.controller.sys;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cy.common.Response;
import com.cy.dao.system.PermissionInfoDAO;
import com.cy.dao.system.RoleInfoDAO;
import com.cy.dao.system.RolePermissionRefDAO;
import com.cy.entity.system.PermissionInfo;
import com.cy.entity.system.RoleInfo;
import com.cy.entity.system.RolePermissionRef;
import com.cy.service.RoleInfoService;
import com.cy.web.controller.sys.base.DataGridAdaptController;
import com.cy.web.dto.param.system.RoleInfoParamDTO;
import com.cy.web.dto.param.system.RolePermissionRefSaveDTO;
import com.cy.web.dto.result.system.RoleInfoListResultDTO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxj on 2017/9/15.
 */
@Controller
@RequestMapping("roleInfo")
public class RoleInfoController extends DataGridAdaptController<RoleInfo, RoleInfoParamDTO> {

    @Autowired
    private RoleInfoDAO roleInfoDAO;

    @Autowired
    private RoleInfoService roleInfoService;

    @Autowired
    private RolePermissionRefDAO rolePermissionRefDAO;

    @Autowired
    private PermissionInfoDAO permissionInfoDAO;

    @Override
    protected List<RoleInfoListResultDTO> getData(RoleInfoParamDTO queryDTO) {
        return roleInfoDAO.listDTO(queryDTO);
    }

    @Override
    public Response doSaveOrUpdate(RoleInfo roleInfo) {
        RoleInfo result = roleInfoService.saveOrUpdate(roleInfo);
        return new Response(result);
    }

    @Override
    protected void doDelete(List<Long> idList) {
        roleInfoDAO.batchDelete(idList);
    }

    @RequestMapping("userRefRoleInfoData")
    @ResponseBody
    public Object userRefRoleInfoData(Long userInfoId) {
        return roleInfoService.userRefRoleInfoData(userInfoId);
    }

    /**
     * 菜单权限
     * @param roleInfoId
     * @param modelMap
     * @return
     */
    @RequestMapping("refPermissionMenu/{roleInfoId}")
    public String refPermissionMenu(@PathVariable Long roleInfoId, ModelMap modelMap) {
        modelMap.put("values", StringUtils.arrayToDelimitedString(rolePermissionRefDAO.getMenuByRoleInfoId(roleInfoId).toArray(), ","));
        modelMap.put("roleInfoId", roleInfoId);
        return genPath("refPermissionMenu");
    }

    @RequestMapping("saveRefPermissionMenu")
    @ResponseBody
    public Response saveRefPermissionMenu(Long roleInfoId, String values) {
        values = values == null ? "" : values;
        List<Long> menuInfoIdList = new ArrayList<Long>();
        for (String menuInfoId : values.split(",")) {
            if (!"".equals(menuInfoId)) {
                menuInfoIdList.add(Long.valueOf(menuInfoId));
            }
        }
        roleInfoService.saveRefPermissionMenu(roleInfoId, menuInfoIdList);
        return new Response(null);
    }

    /**
     * 页面权限
     * @param roleInfoId
     * @param modelMap
     * @return
     */
    @RequestMapping("refPermissionPage/{roleInfoId}")
    public String refPermissionPage(@PathVariable Long roleInfoId, ModelMap modelMap) {
        modelMap.put("roleInfoId", roleInfoId);
        return genPath("refPermissionPage");
    }

    @RequestMapping("listPermData/{roleInfoId}")
    @ResponseBody
    public Object listPermData(@PathVariable Long roleInfoId, @RequestParam(value = "page", defaultValue = "1") Integer pageNum, @RequestParam(value = "rows", defaultValue = "10")Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<RolePermissionRef> list = rolePermissionRefDAO.getByRoleInfoId(roleInfoId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", list.size());
        map.put("rows", list);
        return map;
    }

    @RequestMapping("saveRefPermissionPage")
    @ResponseBody
    public Response saveRefPermissionPage(Long roleInfoId, Long permissionId, String values) {
        RolePermissionRefSaveDTO dto = new RolePermissionRefSaveDTO();
        dto.setRoleId(roleInfoId);
        dto.setPermissionId(permissionId);
        PermissionInfo permissionInfo = permissionInfoDAO.getById(permissionId);
        dto.setPermissionCode(permissionInfo.getCode());
        dto.setPermissionName(permissionInfo.getName());
        if (!StringUtils.isEmpty(values)) {
            List<String> id = new ArrayList<String>();
            List<String> code = new ArrayList<String>();
            List<String> name = new ArrayList<String>();
            for (String item : values.split(",")) {
                String[] split = item.split("-");
                id.add(split[0]);
                code.add(split[1]);
                name.add(split[2]);
            }
            dto.setOperationInfoId(StringUtils.arrayToCommaDelimitedString(id.toArray()));
            dto.setOperationInfoCode(StringUtils.arrayToCommaDelimitedString(code.toArray()));
            dto.setOperationInfoName(StringUtils.arrayToCommaDelimitedString(name.toArray()));
        }
        roleInfoService.saveRefPermissionPage(dto);
        return new Response(null);
    }

    @RequestMapping("getPermissionData")
    @ResponseBody
    public Object getPermissionData() {
        List<PermissionInfo> list = permissionInfoDAO.list(null);
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", "-1");
        jsonObject.put("text", "---请选择---");
        jsonObject.put("selected", "true");
        jsonArray.add(jsonObject);
        for (PermissionInfo item : list) {
            jsonObject = new JSONObject();
            jsonObject.put("id", item.getId());
            jsonObject.put("text", item.getName());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

}
