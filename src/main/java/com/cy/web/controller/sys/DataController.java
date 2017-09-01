package com.cy.web.controller.sys;

import com.cy.common.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by zxj on 2017/8/30.
 */
public abstract class DataController<T> extends BaseController {

    private Class<T> entityClass;

    protected DataController() {
        Type type = getClass().getGenericSuperclass();
        Type trueType = ((ParameterizedType) type).getActualTypeArguments()[0];
        this.entityClass = (Class<T>) trueType;
    }

    @RequestMapping("list")
    public String list() {
        StringBuilder sb = new StringBuilder(entityClass.getSimpleName());
        sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        return String.format("%s/list", sb.toString());
    }

    @RequestMapping("data")
    @ResponseBody
    public abstract Object data(PageInfo pageInfo);

}
