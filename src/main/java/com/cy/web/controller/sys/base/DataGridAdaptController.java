package com.cy.web.controller.sys.base;

import com.cy.common.PageInfo;
import com.cy.common.Response;
import com.cy.common.constant.*;
import com.cy.common.constant.ResponseStatus;
import com.cy.entity.system.MenuInfo;
import com.cy.entity.system.RoleInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxj on 2017/8/30.
 */
public abstract class DataGridAdaptController<T, E> extends BaseController {

    private Class<T> entityClass;

    private String entityClassName;

    protected DataGridAdaptController() {
        Type type = getClass().getGenericSuperclass();
        Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
        this.entityClass = (Class<T>) trueType;
        StringBuilder sb = new StringBuilder(entityClass.getSimpleName());
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        this.entityClassName = sb.toString();
    }

    protected String genPath(String path) {
        return String.format("%s/%s", entityClassName, path);
    }

    @RequestMapping("list")
    public String list(ModelMap modelMap) {
        modelMap.addAttribute("modelName", entityClassName);
        modelMap.addAttribute("editUrl", genPath("edit"));
        modelMap.addAttribute("delUrl", genPath("delete"));
        modelMap.addAttribute("dataUrl", genPath("data"));
        return genPath("list");
    }

    @RequestMapping("edit")
    public String tempFtl(ModelMap modelMap) {
        modelMap.addAttribute("actionUrl", genPath("saveOrUpdate"));
        return genPath("edit");
    }

    @RequestMapping("data")
    @ResponseBody
    public Object data(@RequestParam("page") Integer pageNum, @RequestParam("rows")Integer pageSize, E queryDTO) {
        Map<String, Object> map = new HashMap<String, Object>();
        PageHelper.startPage(pageNum, pageSize);
        List<T> list = getData(queryDTO);
        map.put("total", ((Page)list).getTotal());
        map.put("rows", list);
        doDataOther(map);
        return map;
    }

    protected abstract List<T> getData(E queryDTO);

    protected void doDataOther(Map<String, Object> map) {

    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public abstract Response saveOrUpdate(T t);

    @RequestMapping("delete")
    @ResponseBody
    public Response delete(@RequestBody List<Long> idList) {
        if (!SecurityUtils.getSubject().isPermitted(String.format("%s:delete", entityClassName))) {
            return new Response(ResponseStatus.NO_PERMISSION);
        }
        doDelete(idList);
        return new Response(null);
    }

    protected abstract void doDelete(List<Long> idList);

    @RequestMapping("{tempName}")
    public String tempFtl(@PathVariable String tempName) {
        return genPath(tempName);
    }

}
