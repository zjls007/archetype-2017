package com.cy.common;

/**
 * Created by zxj on 2017/2/17.
 */
public enum ByteBooleanEnum {

    FAILED((byte) 0),
    SUCCESS((byte) 1);

    private Byte code;

    ByteBooleanEnum(Byte code) {
        this.code = code;
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }
}
