package com.cy.common.aspect;

import com.cy.common.annotation.ParamValid;
import com.cy.common.exception.ParamException;
import com.cy.common.util.ValidateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by zxj on 2017/5/8.
 */
@Component
@Aspect
@Order(1) // 这里的order要比 dao.xml中transactionManager的order要大
public class ParamValidAspect {

    @Pointcut("execution(* com.cy.service.*.*(..))")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Object[] params = point.getArgs();
        for (Object param : params) {
            if (param.getClass().getAnnotation(ParamValid.class) != null) {
                if (param == null) {
                    throw new ParamException("参数对象不能为空!");
                } else {
                    ValidateUtil.validate(param);
                }
            }
        }
        return point.proceed();
    }

}
