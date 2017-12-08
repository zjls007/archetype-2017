package com.cy.web.controller.front.base;

import com.cy.common.Response;
import com.cy.common.constant.Constants;
import com.cy.common.constant.ResponseStatus;
import com.cy.common.exception.SystemException;
import com.cy.web.controller.admin.base.BaseController;
import com.cy.web.controller.admin.base.DataGridAdaptController;
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
public abstract class LayerTableAdaptController<T, E> extends DataGridAdaptController<T, E> {

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

}
