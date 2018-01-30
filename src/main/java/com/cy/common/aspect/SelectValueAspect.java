package com.cy.common.aspect;

import com.cy.common.annotation.SelectVal;
import com.cy.common.util.SelectUtil;
import com.cy.web.vo.SelectOptionVO;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zxj on 2017/5/8.
 * 开启aop功能只需配置 <aop:aspectj-autoproxy/>
 */
@Aspect
public class SelectValueAspect {

    /**
     * 切入点 execution表达式
     * execution(*[表示返回值为任意类型] com.cy[包名].*[包下的任意类].*[任意方法](..[任意参数]))
     * 强调:
     *      (一)aop的底层实现为动态代理，因为类名只能访问公共方法，所以切入点只能切入到 public 方法上
     *      (二)被切入的方法中调用的其他方法不会被aop起作用 如：AService 中 public void a() 方法调用 public void b()方法，在表达式 execution(* *.b())不会对b起作用
     *              解决方案1.把aService注入到本身， 使用 aService.b()调用b方法
     *              解决方案2. 在AService的a方法中调用b改为：((AService)AopContext.currentProxy()).b()， 前提是开启 <aop:aspectj-autoproxy proxy-target-class="true" expose-proxy="true"/> 的 expose-proxy
     */
    @Pointcut("execution(* com.cy.web.controller.front.base.*.*(..)) && @annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void dataPointcut() {

    }

    @Pointcut("execution(* com.cy.web.controller.front..*.getModel(..))")
    public void getModelPointcut() {

    }

    @AfterReturning(pointcut = "getModelPointcut()", returning="obj")
    public Object afterGetModel(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            SelectVal annotation = field.getAnnotation(SelectVal.class);
            if (annotation != null) {
                List<SelectOptionVO> voList = SelectUtil.selectMap.get(annotation.key());
                convertSelect(obj, field.getName(), voList);
            }
        }
        if (obj.getClass().getSuperclass() != null) {
            for (Field field : obj.getClass().getSuperclass().getDeclaredFields()) {
                SelectVal annotation = field.getAnnotation(SelectVal.class);
                if (annotation != null) {
                    List<SelectOptionVO> voList = SelectUtil.selectMap.get(annotation.key());
                    convertSelect(obj, field.getName(), voList);
                }
            }
        }
        return obj;
    }

    @AfterReturning(returning="object", pointcut="dataPointcut()")
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
