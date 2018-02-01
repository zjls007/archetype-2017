package com.cy.common.emun;

public enum Navigation {

    HOME("home", "首页"),
    TASK("task", "任务列表"),
    USER_INFO("userInfo", "用户管理"),
    ;

    private String code;
    private String name;

    Navigation(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean contains(String code) {
        return convert(code) != null;
    }

    public static Navigation convert(String code) {
        if (code != null && !"".equals(code)) {
            for (Navigation item : Navigation.values()) {
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