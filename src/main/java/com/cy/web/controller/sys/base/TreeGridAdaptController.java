package com.cy.web.controller.sys.base;

import com.cy.common.Response;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxj on 2017/10/11.
 */
public abstract class TreeGridAdaptController<T> extends BaseController {

    private Class<T> entityClass;

    private String entityClassName;

    protected TreeGridAdaptController() {
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
        modelMap.addAttribute("dataUrl", genPath("data"));
        modelMap.addAttribute("actionUrl", genPath("saveOrUpdate"));
        return genPath("list");
    }

    @RequestMapping("data")
    @ResponseBody
    public Object data() {
        Map<String, Object> map = new HashMap<String, Object>();
        List<T> list = getData();
        map.put("total", list.size());
        map.put("rows", list);
        doDataOther(map);
        return map;
    }

    protected abstract List<T> getData();

    protected void doDataOther(Map<String, Object> map) {

    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public abstract Response saveOrUpdate(String jsonStr);

}
