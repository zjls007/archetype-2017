package com.cy.common.constant;

/**
 * Created by zxj on 2017/5/8.
 */
public enum ResponseStatus {

    SUCCESS("success", "成功!"),
    PARAM_ERROR("param_error", "参数错误!"),
    VALID_ERROR("valid_error", "验证错误!"), // 如字段违反唯一约束等
    EXCEPTION("exception", "异常!"),
    ACCESS_DENIED("un_login", "未登陆!"),
    NO_PERMISSION("no_permission", "无权限!"),

    USER_NAME_ERROR("user_name_error", "用户名不正确!"),
    PASSWORD_ERROR("password_error", "密码不正确!"),
    ACCOUNT_DENIED("account_denied", "账户被锁定!"),
    ;

    private String code;

    private String message;

    ResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
