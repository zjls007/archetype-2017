package com.cy.common.proxy;

import com.cy.common.annotation.SelectVal;
import com.cy.common.util.SelectUtil;
import com.cy.web.vo.SelectOptionVO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zxj on 2017/5/8.
 */
@Aspect
public class SelectValueProxy {

    @Pointcut("execution(* com.cy.web.controller.front.base.*.*(..)) && @annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void responseBodyPointcut() {

    }

    @AfterReturning(returning="object", pointcut="responseBodyPointcut()")
    public Object around(Object object) throws Throwable {
        List list = (List)((HashMap) object).get("data");
        if (list != null && !list.isEmpty()) {
            for (Object item : list) {
                boolean hasSelect = false;
                for (Field field : item.getClass().getDeclaredFields()) {
                    SelectVal annotation = field.getAnnotation(SelectVal.class);
                    if (annotation != null) {
                        hasSelect = true;
                        List<SelectOptionVO> voList = SelectUtil.selectMap.get(annotation.key());
                        convertSelect(item, field.getName(), voList);
                    }
                }
                // 没有找到需要转换的属性退出循环
                if (!hasSelect) {
                    break;
                }
            }
        }
        return object;
    }

    private void convertSelect(Object object, String fieldName, List<SelectOptionVO> voList) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(fieldName, object.getClass());
            Object value = pd.getReadMethod().invoke(object);
            Method setMethod = pd.getWriteMethod();
            for (SelectOptionVO vo : voList) {
                if (vo.getValue().equals(value)) {
                    setMethod.invoke(object, vo.getName());
                    break;
                }
            }
        } catch (Exception e) {
            // 没有get,set方法的字段跳过
        }
    }

}
