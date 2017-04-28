package com.cy.common.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by zxj on 2017/4/27.
 */
public class CustomAuthcFilter extends FormAuthenticationFilter {

    @Override
    protected final boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        boolean isAccessAllowed = super.isAccessAllowed(request, response, mappedValue);
        if (!isAccessAllowed) {
            request.setAttribute("error", "error");
        }
        return true;
    }

}
