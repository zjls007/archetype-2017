package com.cy.common.filter;

import com.cy.common.Response;
import com.cy.common.constant.Constants;
import com.cy.common.constant.ResponseStatus;
import com.cy.common.exception.SystemException;
import com.cy.common.interceptor.AllIntercept;
import com.cy.common.util.SystemURL;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zxj on 2017/5/10.
 */
public class IFormAuthenticationFilter extends FormAuthenticationFilter {

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if(this.isLoginRequest(request, response)) {
            if(this.isLoginSubmission(request, response)) {
                return this.executeLogin(request, response);
            } else {
                return true;
            }
        } else {
            String url = ((HttpServletRequest) request).getRequestURL().toString();
            if (SystemURL.isAjaxUrl(url)) {
                new Response(ResponseStatus.ACCESS_DENIED, "请重新登录!").send((HttpServletResponse) response);
                return false;
            }
            this.saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }

    protected final boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return super.isAccessAllowed(request, response, mappedValue);
    }

    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        // 走ajax
        Long currentUserId = (Long) SecurityUtils.getSubject().getSession().getAttribute(Constants.CURRENT_USER_ID);
        new Response(currentUserId).send((HttpServletResponse) response);
        // 页面重定向
//        this.issueSuccessRedirect(request, response);
        return false;
    }

    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        String message = "";
        if (e instanceof UnknownAccountException) {
            message = "账号不存在";
        } else if (e instanceof IncorrectCredentialsException) {
            message = "密码不正确";
        } else if (e instanceof LockedAccountException) {
            message = "账号被锁定";
        }
        new Response(ResponseStatus.PARAM_ERROR, message).send((HttpServletResponse) response);
        return false;
        // 页面重定向
//        this.setFailureAttribute(request, e);
//        return true;
    }

}
