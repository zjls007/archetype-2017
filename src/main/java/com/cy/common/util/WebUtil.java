package com.cy.common.util;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;

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

    public static boolean isAjaxRequest(HandlerMethod handlerMethod) {
        // 通过controller方法上有没有@ResponseBody判断是否为ajax请求
        boolean ajaxRequest = handlerMethod.getMethodAnnotation(ResponseBody.class) != null;
        if (!ajaxRequest) {
            ajaxRequest = handlerMethod.getBeanType().getAnnotation(RestController.class) != null;
        }
        return ajaxRequest;
    }

}
