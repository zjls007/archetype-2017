package com.cy.entity.system.enums;

/**
 * Created by zxj on 2018-01-04 17:01:54.
 * task - type 类型（assign-指派、take-认领）, not null
 */
public enum TaskType {

    ASSIGN("assign", "指派"),
    TAKE("take", "认领"),
    ;

    private String code;
    private String name;

    TaskType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean contains(String code) {
        return convert(code) != null;
    }

    public static TaskType convert(String code) {
        if (code != null && !"".equals(code)) {
            for (TaskType item : TaskType.values()) {
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