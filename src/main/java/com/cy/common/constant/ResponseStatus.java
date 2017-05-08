package com.cy.common.constant;

/**
 * Created by zxj on 2017/5/8.
 */
public enum ResponseStatus {

    SUCCESS(0, "成功"),
    PARAM_ERROR(1, "参数错误");

    private Integer code;

    private String message;

    ResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

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

}
