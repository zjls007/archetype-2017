package com.cy.common.filter;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by zxj on 2017/4/27.
 */
public class IPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

    @Override
    public final boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        boolean isAccessAllowed = super.isAccessAllowed(request, response, mappedValue);
        if (!isAccessAllowed) {
            request.setAttribute("prem", mappedValue);
        }
        return true;
    }

}
