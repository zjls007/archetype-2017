package com.cy.entity.system.enums;

/**
 * Created by zxj on 2018-01-04 17:01:54.
 * task_user - state 状态（read-已读、take-认领）, not null
 */
public enum TaskUserState {

    READ("read", "已读"),
    TAKE("take", "认领"),
    ;

    private String code;
    private String name;

    TaskUserState(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean contains(String code) {
        return convert(code) != null;
    }

    public static TaskUserState convert(String code) {
        if (code != null && !"".equals(code)) {
            for (TaskUserState item : TaskUserState.values()) {
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