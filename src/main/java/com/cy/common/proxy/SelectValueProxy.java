package com.cy.common.proxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by zxj on 2017/5/8.
 */
@Aspect
public class SelectValueProxy {

    @Pointcut("execution(* com.cy.web.controller.front.base.*.*(..)) && @annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        return result;
    }

}
