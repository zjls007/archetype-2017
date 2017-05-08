package com.cy.common;

/**
 * Created by zxj on 2017/5/8.
 */
public class Response {

    public Response(Object data) {
        this.code = 0;
        this.message = "成功";
        this.data = data;
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
