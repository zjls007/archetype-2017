package com.cy.common.resolver;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.RequestParamMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zxj on 2017/4/25.
 * SpringMvc接收参数最底层实现参考:{@link WebUtils#getParametersStartingWith(ServletRequest, String)}
 * [
 *  寻找过程：{@link RequestMappingHandlerAdapter#argumentResolvers} 方法参数解析器
 *  <pre>
 *      strParam(String param1) 采用 {@link RequestParamMethodArgumentResolver}解析普通类型
 *      beanParam(Bean bean1) 采用 {@link ServletModelAttributeMethodProcessor} 解析对象类型
 *      1.{@link ServletModelAttributeMethodProcessor#bindRequestParameters(WebDataBinder, NativeWebRequest)}
 *      2.{@link ServletRequestDataBinder}
 *      3.{@link ServletRequestParameterPropertyValues}
 *      4.public ServletRequestParameterPropertyValues(ServletRequest request, String prefix, String prefixSeparator) {
 *          super(WebUtils.getParametersStartingWith(request, prefix != null?prefix + prefixSeparator:null));
 *        }
 *  </pre>
 * ]
 * 由上可知获取参数最底层的方法为 {@link HttpServletRequest#getParameterValues(String)}
 * 所以实现 {@link HttpServletRequestWrapper} 重写方法实现对应功能
 */
public class HttpServletRequestWrapper1 extends HttpServletRequestWrapper {

    HttpServletRequest request;

    public HttpServletRequestWrapper1(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    public String getParameter(String name) {
        boolean getMethod = request.getMethod().equalsIgnoreCase("GET");
        String value = request.getParameter(name);
        return trimAndEncoding(value, getMethod);
    }

    public String[] getParameterValues(String name) {
        boolean getMethod = request.getMethod().equalsIgnoreCase("GET");
        String[] values = request.getParameterValues(name);
        List<String> list = new ArrayList<String>();
        if (values != null && values.length > 0) {
            for (String val : values) {
                list.add(trimAndEncoding(val, getMethod));
            }
        }
        return list.toArray(new String[list.size()]);
    }

    private String trimAndEncoding(String val, boolean getMethod) {
        if (val != null) {
            val = val.trim();
            if (getMethod) {
                try {
                    val = new String(val.getBytes("iso-8859-1"), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return val;
    }
}
