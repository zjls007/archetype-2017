package com.cy.common.interceptor;

import com.cy.common.constant.Constants;
import com.cy.common.util.WebUtil;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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
        String scheme = httpServletRequest.getScheme();
        String serverName = httpServletRequest.getServerName();
        int port = httpServletRequest.getServerPort();
        String path = httpServletRequest.getContextPath();
        String basePath = scheme + "://" + serverName + ":" + port + path;
        if (basePath.lastIndexOf("/") != (basePath.length() - 1)) {
            basePath += "/";
        }
        httpServletRequest.setAttribute("basePath", basePath);
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
