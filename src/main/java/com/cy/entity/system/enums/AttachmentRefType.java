package com.cy.entity.system.enums;

/**
 * Created by zxj on 2018-01-08 16:05:04.
 * attachment_ref - ref_type 关联类型（task-任务、other-其他）, not null
 */
public enum AttachmentRefType {

    TASK("task", "任务"),
    OTHER("other", "其他"),
    ;

    private String code;
    private String name;

    AttachmentRefType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean contains(String code) {
        return convert(code) != null;
    }

    public static AttachmentRefType convert(String code) {
        if (code != null && !"".equals(code)) {
            for (AttachmentRefType item : AttachmentRefType.values()) {
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