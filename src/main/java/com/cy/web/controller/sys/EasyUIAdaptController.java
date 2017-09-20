package com.cy.web.controller.sys;

import com.cy.common.PageInfo;
import com.cy.common.Response;
import com.cy.entity.system.MenuInfo;
import com.cy.entity.system.RoleInfo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
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
public abstract class EasyUIAdaptController<T, E> extends BaseController {

    private Class<T> entityClass;

    protected EasyUIAdaptController() {
        Type type = getClass().getGenericSuperclass();
        Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
        this.entityClass = (Class<T>) trueType;
    }

    @RequestMapping("list")
    public String list(ModelMap modelMap) {
        StringBuilder sb = new StringBuilder(entityClass.getSimpleName());
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        modelMap.addAttribute("delUrl", String.format("%s/delete", sb.toString()));
        modelMap.addAttribute("dataUrl", String.format("%s/data", sb.toString()));
        modelMap.addAttribute("actionUrl", String.format("%s/saveOrUpdate", sb.toString()));
        return String.format("%s/list", sb.toString());
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
        return doDelete(idList);
    }

    protected abstract Response doDelete(List<Long> idList);

}
