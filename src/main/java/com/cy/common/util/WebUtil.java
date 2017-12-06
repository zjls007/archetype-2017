package com.cy.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zxj on 2017/12/6.
 */
public class WebUtil {

    public static String getBasePath(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();
        String path = request.getContextPath();
        String basePath = scheme + "://" + serverName + ":" + port + path;
        if (basePath.lastIndexOf("/") != (basePath.length() - 1)) {
            basePath += "/";
        }
        return basePath;
    }

}
