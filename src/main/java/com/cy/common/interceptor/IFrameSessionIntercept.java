package com.cy.common.interceptor;

import com.cy.common.Response;
import com.cy.common.constant.ResponseStatus;
import com.cy.common.util.WebUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

/**
 * Created by zxj on 2017/9/22.
 */
public class IFrameSessionIntercept implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return true;
        }
        boolean ajaxRequest = WebUtil.isAjaxRequest((HandlerMethod) handler);
        if (ajaxRequest) {
            httpServletResponse.setStatus(403);
        } else {
            httpServletResponse.sendRedirect(WebUtil.getBasePath(httpServletRequest)+"/front/timeOut");
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
