package com.cy.entity.system.enums;

/**
 * Created by zxj on 2018-01-07 08:58:37.
 * task - difficult 任务状态（easy-简单、normal-一般、hard-困难）, not null
 */
public enum TaskDifficult {

    EASY("easy", "简单"),
    NORMAL("normal", "一般"),
    HARD("hard", "困难"),
    ;

    private String code;
    private String name;

    TaskDifficult(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean contains(String code) {
        return convert(code) != null;
    }

    public static TaskDifficult convert(String code) {
        if (code != null && !"".equals(code)) {
            for (TaskDifficult item : TaskDifficult.values()) {
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