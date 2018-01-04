package com.cy.entity.system.enums;

/**
 * Created by zxj on 2018-01-04 17:01:54.
 * task - state 任务状态（publish-发布、take-认领、begin-开始、wait-挂起、complete-完成）, not null
 */
public enum TaskState {

    PUBLISH("publish", "发布"),
    TAKE("take", "认领"),
    BEGIN("begin", "开始"),
    WAIT("wait", "挂起"),
    COMPLETE("complete", "完成"),
    ;

    private String code;
    private String name;

    TaskState(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean contains(String code) {
        return convert(code) != null;
    }

    public static TaskState convert(String code) {
        if (code != null && !"".equals(code)) {
            for (TaskState item : TaskState.values()) {
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