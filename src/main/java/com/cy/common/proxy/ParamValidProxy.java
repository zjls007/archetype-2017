package com.cy.common.proxy;

import com.cy.common.annotation.ParamValid;
import com.cy.common.util.ValidateUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.ValidationException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by zxj on 2017/5/8.
 */
@Component
@Aspect
@Order(1) // 这里的order要比 dao.xml中transactionManager的order要大
public class ParamValidProxy {

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
                    throw new ValidationException("参数对象不能为空!");
                } else {
                    ValidateUtil.validate(param);
                }
            }
        }
        return point.proceed();
    }

}
