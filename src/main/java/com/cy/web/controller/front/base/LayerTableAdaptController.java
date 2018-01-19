package com.cy.web.controller.front.base;

import com.cy.common.Response;
import com.cy.common.constant.ResponseStatus;
import com.cy.common.util.SelectUtil;
import com.cy.web.controller.admin.base.DataGridAdaptController;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.ModelMap;
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
    protected void doList(ModelMap modelMap) {
        modelMap.addAttribute("selectMap", SelectUtil.selectMap);
        modelMap.addAttribute("editUrl", genPath("edit"));
        modelMap.addAttribute("modelNameCN", getModelNameCN());
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

    @RequestMapping({"edit/{id}", "edit"})
    public String edit(@PathVariable(required=false) Long id, ModelMap modelMap) {
        modelMap.addAttribute("selectMap", SelectUtil.selectMap);
        modelMap.addAttribute("modelNameCN", getModelNameCN());
        if (id != null) {
            modelMap.addAttribute("entity", getModel(id, modelMap));
        }
        return genPath("edit");
    }

    @RequestMapping("view/{id}")
    public String view(@PathVariable(required=false) Long id, ModelMap modelMap) {
        modelMap.addAttribute("selectMap", SelectUtil.selectMap);
        modelMap.addAttribute("modelNameCN", getModelNameCN());
        if (id != null) {
            modelMap.addAttribute("entity", getModel(id, modelMap));
        }
        return genPath("view");
    }

    protected abstract Object getModel(Long id, ModelMap modelMap);

}
