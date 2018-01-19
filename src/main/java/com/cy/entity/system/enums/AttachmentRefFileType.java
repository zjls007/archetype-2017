package com.cy.entity.system.enums;

/**
 * Created by zxj on 2018-01-08 16:05:04.
 * attachment_ref - file_type 关联类型（img-图片、attachment-附件）, not null
 */
public enum AttachmentRefFileType {

    IMG("img", "图片"),
    ATTACHMENT("attachment", "附件"),
    ;

    private String code;
    private String name;

    AttachmentRefFileType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean contains(String code) {
        return convert(code) != null;
    }

    public static AttachmentRefFileType convert(String code) {
        if (code != null && !"".equals(code)) {
            for (AttachmentRefFileType item : AttachmentRefFileType.values()) {
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