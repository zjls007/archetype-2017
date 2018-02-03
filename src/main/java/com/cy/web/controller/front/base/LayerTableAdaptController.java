package com.cy.web.controller.front.base;

import com.cy.common.Response;
import com.cy.common.constant.ResponseStatus;
import com.cy.common.emun.Navigation;
import com.cy.common.util.SelectUtil;
import com.cy.web.controller.admin.base.DataGridAdaptController;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.subject.Subject;
import org.springframework.aop.framework.AopContext;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zxj on 2017/8/30.
 */
public abstract class LayerTableAdaptController<T, E> extends DataGridAdaptController<T, E> {

    @Override
    protected void doList(ModelMap modelMap, String nav) {
        globalAttribute(modelMap);
        modelMap.addAttribute("editUrl", genPath("edit"));
        modelMap.addAttribute("nav", nav(nav, navName("列表")));
    }

    private String navName(String name) {
        StringBuilder sb = new StringBuilder();
        sb.append(Navigation.convert(entityClassName).getName());
        sb.append(name);
        return sb.toString();
    }

    protected String nav(String parent, String current) {
        if (StringUtils.isEmpty(parent)) {
            return current;
        }
        return String.format("%s,%s", parent, current);
    }

    public abstract String getModelNameCN();

    @RequestMapping("data")
    @ResponseBody
    public Object data(@RequestParam("page") Integer pageNum, @RequestParam("limit")Integer pageSize, E queryDTO) {
        Subject subject = getSubject();
        if (!subject.isPermitted(genPerm("query")) && !otherSuperPerm()) {
            return new Response(ResponseStatus.NO_PERMISSION);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        PageHelper.startPage(pageNum, pageSize);
        List<? extends T> list = getData(queryDTO);
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", ((Page) list).getTotal());
        map.put("data", list);
        doDataOther(map);
        return map;
    }

    @RequestMapping({"edit", "edit/{id}"})
    public String edit(@PathVariable(required=false) Long id, ModelMap modelMap, String nav) {
        globalAttribute(modelMap);
        modelMap.addAttribute("nav", nav(nav, navName("编辑")));
        if (id != null) {
            modelMap.addAttribute("entity", getModel(id, modelMap));
        }
        return genPath("edit");
    }

    @RequestMapping({"view/{id}"})
    public String view(@PathVariable(required=false) Long id, ModelMap modelMap, String nav) {
        globalAttribute(modelMap);
        modelMap.addAttribute("nav", nav(nav, navName("查看")));
        if (id != null) {
            modelMap.addAttribute("entity", ((LayerTableAdaptController)AopContext.currentProxy()).getModel(id, modelMap));
        }
        return genPath("view");
    }

    protected void globalAttribute(ModelMap modelMap) {
        modelMap.addAttribute("userId", getCurrentUserId());
        modelMap.addAttribute("selectMap", SelectUtil.selectMap);
        modelMap.addAttribute("modelNameCN", getModelNameCN());
    }

    protected abstract Object getModel(Long id, ModelMap modelMap);

}
