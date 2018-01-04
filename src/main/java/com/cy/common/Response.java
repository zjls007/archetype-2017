package com.cy.common;


import com.cy.common.constant.ResponseStatus;

/**
 * Created by zxj on 2017/5/8.
 */
public class Response {

    /**
     * 参考: {@link ResponseStatus#code}
     */
    private String code;

    /**
     * 参考：{@link ResponseStatus#message}
     */
    private String message;

    /**
     * 返回的数据
     */
    private Object data;

    public Response() {
        this.code = ResponseStatus.SUCCESS.getCode();
        this.message = ResponseStatus.SUCCESS.getMessage();
    }

    public Response(Object obj) {
        if (obj instanceof ResponseStatus) {
            ResponseStatus responseStatus = (ResponseStatus) obj;
            this.code = responseStatus.getCode();
            this.message = responseStatus.getMessage();
        } else {
            this.code = ResponseStatus.SUCCESS.getCode();
            this.message = ResponseStatus.SUCCESS.getMessage();
            this.data = obj;
        }
    }

    public Response(ResponseStatus responseStatus, String message) {
        this.code = responseStatus.getCode();
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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
