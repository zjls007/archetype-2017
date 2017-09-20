package com.cy.web.controller.sys;

import com.cy.common.PageInfo;
import com.cy.common.Response;
import com.cy.entity.system.MenuInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxj on 2017/8/30.
 */
public abstract class EasyUIAdaptController<T> extends BaseController {

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
    public Object data(PageInfo pageInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<T> list = getData();
        map.put("total", list.size());
        map.put("rows", list);
        doDataOther(map);
        return map;
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public abstract Response saveOrUpdate(T t);

    @RequestMapping("delete")
    public Response delete(@RequestBody List<Long> idList) {
        return doDelete(idList);
    }

    protected abstract Response doDelete(List<Long> idList);

    protected abstract List<T> getData();

    protected void doDataOther(Map<String, Object> map) {

    }

}
