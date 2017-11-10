package com.cy.common;


import com.cy.common.constant.ResponseStatus;
import com.cy.common.exception.SystemException;
import com.cy.common.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zxj on 2017/5/8.
 */
public class Response {

    public Response(Object data) {
        this.code = 0;
        this.message = "成功";
        this.data = data;
    }

    public Response(ResponseStatus responseStatus) {
        if (responseStatus == null) {
            this.code = 0;
            this.message = "成功";
            this.data = data;
        } else {
            this.code = responseStatus.getCode();
            this.message = responseStatus.getMessage();
        }
    }

    public Response(ResponseStatus responseStatus, String message) {
        this.code = responseStatus.getCode();
        this.message = message;
    }

    public void send(HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-RolePermissionRefType", "application/json");
        try {
            PrintWriter out = response.getWriter();
            out.write(JsonUtil.toJsonStr(this));
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new SystemException("写json数据失败", e);
        }
    }

    /**
     * 结果
     */
    private Integer code;

    private String message;

    private Object data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
