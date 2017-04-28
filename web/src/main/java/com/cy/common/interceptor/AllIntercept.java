package com.cy.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zxj on 2017/4/11.
 */
public class AllIntercept implements HandlerInterceptor {

    public Logger logger = LoggerFactory.getLogger(AllIntercept.class);

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            boolean responseBody = handlerMethod.getMethod().getAnnotation(ResponseBody.class) != null;
            if (!responseBody) {
                responseBody = handlerMethod.getBeanType().getAnnotation(RestController.class) != null;
            }
            if (responseBody) {
                if (httpServletRequest.getAttribute("prem") != null) {
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    logger.error("必须要有{}权限", objectMapper.writeValueAsString(httpServletRequest.getAttribute("prem")));
//                    HttpUtil.writeJson(httpServletResponse, "权限不足!");
                    return false;
                }
            } else {

            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
