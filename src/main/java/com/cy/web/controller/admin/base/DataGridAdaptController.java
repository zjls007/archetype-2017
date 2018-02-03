package com.cy.web.controller.admin.base;

import com.cy.common.Response;
import com.cy.common.constant.Constants;
import com.cy.common.constant.ResponseStatus;
import com.cy.common.exception.ValidException;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
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

    protected String entityClassName;

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

    protected String genPerm(String permStr) {
        return String.format("%s:%s", entityClassName, permStr);
    }

    /**
     * 系统管理员、开发人员 角色拥有特色权限（不需要赋值权限代码）
     * @return
     */
    protected boolean otherSuperPerm() {
        Subject subject = getSubject();
        return subject.hasRole(Constants.ROLE_SYS_ADMIN) || subject.hasRole(Constants.DEVELOPER);
    }

    @RequestMapping("list")
    public String list(ModelMap modelMap, String nav) {
        modelMap.addAttribute("modelName", entityClassName);
        modelMap.addAttribute("editUrl", genPath("edit1"));
        modelMap.addAttribute("delUrl", genPath("delete"));
        modelMap.addAttribute("dataUrl", genPath("data"));
        // 按钮权限
        modelMap.addAttribute("addPerm", getSubject().isPermitted(genPerm("add")) || otherSuperPerm());
        modelMap.addAttribute("modifyPerm", getSubject().isPermitted(genPerm("modify")) || otherSuperPerm());
        modelMap.addAttribute("deletePerm", getSubject().isPermitted(genPerm("delete")) || otherSuperPerm());
        doList(modelMap, nav);
        return genPath("list");
    }

    protected void doList(ModelMap modelMap, String nav) {

    }

    @RequestMapping("edit1")
    public String tempFtl(ModelMap modelMap) {
        modelMap.addAttribute("actionUrl", genPath("saveOrUpdate"));
        return genPath("edit");
    }

    @RequestMapping("data")
    @ResponseBody
    public Object data(@RequestParam("page") Integer pageNum, @RequestParam("rows")Integer pageSize, E queryDTO) {
        Subject subject = getSubject();
        if (!subject.isPermitted(genPerm("query")) && !otherSuperPerm()) {
            return new Response(ResponseStatus.NO_PERMISSION);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        PageHelper.startPage(pageNum, pageSize);
        List<? extends T> list = getData(queryDTO);
        map.put("total", ((Page)list).getTotal());
        map.put("rows", list);
        doDataOther(map);
        return map;
    }

    protected abstract List<? extends T> getData(E queryDTO);

    protected void doDataOther(Map<String, Object> map) {

    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Response saveOrUpdate(T t) {
        Long id = null;
        try {
            Method m = t.getClass().getMethod("getId");
            id = (Long) m.invoke(t);
        } catch (Exception e) {
            throw new ValidException("找不到id");
        }
        saveOrUpdatePerm(id);
        return doSaveOrUpdate(t);
    }

    protected void saveOrUpdatePerm(Long id) {
        if (id == null) {
            if (!SecurityUtils.getSubject().isPermitted(genPerm("add")) && !otherSuperPerm()) {
                throw new ValidException(ResponseStatus.NO_PERMISSION.getMessage());
            }
        } else {
            if (!SecurityUtils.getSubject().isPermitted(genPerm("modify")) && !otherSuperPerm()) {
                throw new ValidException(ResponseStatus.NO_PERMISSION.getMessage());
            }
        }
    }

    public abstract Response doSaveOrUpdate(T t);

    @RequestMapping("delete")
    @ResponseBody
    public Response delete(@RequestBody List<Long> idList) {
        if (!SecurityUtils.getSubject().isPermitted(genPerm("delete")) && !otherSuperPerm()) {
            return new Response(ResponseStatus.NO_PERMISSION);
        }
        doDelete(idList);
        return new Response();
    }

    protected abstract void doDelete(List<Long> idList);

    @RequestMapping("{tempName}")
    public String tempFtl(@PathVariable String tempName) {
        return genPath(tempName);
    }

}
