package com.cy.common.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zxj on 2017/3/31.
 * 全局异常解析器(这个类只要被spring加载到就会起作用，可以放在xml中也可以类上打@Component)
 * 实现Ordered接口，把优先级设置为其它所有spring默认异常解析器之前执行，这样能处理controller层接受参数是类型转换错误（页面报http400）
 */
public class GlobalExceptionResolver implements HandlerExceptionResolver, Ordered {

    Logger LOG = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object handler, Exception e) {
        // 通过controller方法上有没有@ResponseBody判断是否为ajax请求
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        boolean responseBody = handlerMethod.getMethod().getAnnotation(ResponseBody.class) != null;
        responseBody = handlerMethod.getBeanType().getAnnotation(RestController.class) != null;
        LOG.error("", e);
        return null;
    }

}
