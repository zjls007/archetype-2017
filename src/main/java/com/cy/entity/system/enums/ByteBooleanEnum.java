package com.cy.entity.system.enums;

/**
 * Created by zxj on 2017-12-06 14:11:31.
 * 共用byte boolean枚举
 */
public enum ByteBooleanEnum {

    FALSE((byte)0, "否"),
    TRUE((byte)1, "是"),
    ;

    private Byte code;
    private String name;

    ByteBooleanEnum(Byte code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean contains(Byte code) {
        return convert(code) != null;
    }

    public static ByteBooleanEnum convert(Byte code) {
        if (code != null) {
            for (ByteBooleanEnum item : ByteBooleanEnum.values()) {
                if (code.equals(item.getCode())) {
                     return item;
                }
             }
        }
        return null;
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}