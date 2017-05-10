package com.cy.common.util;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zxj on 2017/5/10.
 */
public class SystemURL {

    private static Set<String> urls = new HashSet<String>();

    private static Set<String> ajaxUrl = new HashSet<String>();

    static {
        RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) ApplicationContextHelper.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (RequestMappingInfo rmi : handlerMethods.keySet()) {
            HandlerMethod handlerMethod = handlerMethods.get(rmi);
            PatternsRequestCondition prc = rmi.getPatternsCondition();
            Set<String> patterns = prc.getPatterns();
            for (String uStr : patterns) {
                if (handlerMethod.getMethodAnnotation(ResponseBody.class) != null || handlerMethod.getBeanType().getAnnotation(RestController.class) != null) {
                    ajaxUrl.add(uStr);
                }
                urls.add(uStr);
            }
        }
    }

    public static Set<String> getAllURL() {
        return urls;
    }

    public static boolean isValidUrl(String url) {
        if (url != null) {
            for (String s : urls) {
                if (url.endsWith(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAjaxUrl(String url) {
        if (url != null) {
            for (String s : ajaxUrl) {
                if (url.endsWith(s)) {
                    return true;
                }
            }
        }
        return false;
    }

}
