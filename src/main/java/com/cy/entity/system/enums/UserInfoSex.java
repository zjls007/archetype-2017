package com.cy.entity.system.enums;

/**
 * Created by zxj on 2017-12-06 14:19:19.
 * t_user_info - sex 性别（Man-男, Woman-女）
 */
public enum UserInfoSex {

    MAN("Man", "男"),
    WOMAN("Woman", "女"),
    ;

    private String code;
    private String name;

    UserInfoSex(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean contains(String code) {
        return convert(code) != null;
    }

    public static UserInfoSex convert(String code) {
        if (code != null && !"".equals(code)) {
            for (UserInfoSex item : UserInfoSex.values()) {
                if (code.equals(item.getCode())) {
                     return item;
                }
             }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}