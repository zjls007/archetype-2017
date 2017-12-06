package com.cy.common.interceptor;

import com.cy.common.util.WebUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zxj on 2017/9/22.
 */
public class IFrameSessionIntercept implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return true;
        }
        httpServletResponse.sendRedirect(WebUtil.getBasePath(httpServletRequest)+"front/timeOut");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
