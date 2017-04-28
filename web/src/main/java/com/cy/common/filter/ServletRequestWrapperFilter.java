package com.cy.common.filter;

import com.cy.common.resolver.HttpServletRequestWrapper1;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by zxj on 2017/4/25.
 */
public class ServletRequestWrapperFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequestWrapper1 request = new HttpServletRequestWrapper1((HttpServletRequest) servletRequest);
        filterChain.doFilter(request, servletResponse);
    }

    public void destroy() {

    }

}
