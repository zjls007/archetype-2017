package com.cy.common.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zxj on 2017/12/6.
 */
public class WebUtil {

    public static void write(HttpServletResponse response, Object object) {
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-RolePermissionRefType", "application/json");
        try {
            PrintWriter out = response.getWriter();
            out.write(JSONObject.toJSONString(object));
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException("写json数据失败", e);
        }
    }

    public static String getBasePath(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int port = request.getServerPort();
        String path = request.getContextPath();
        String basePath = scheme + "://" + serverName + ":" + port + path;
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
