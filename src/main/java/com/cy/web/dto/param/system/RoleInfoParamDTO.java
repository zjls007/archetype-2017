package com.cy.web.dto.param.system;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by zxj on 2017/11/9.
 */
public class RoleInfoParamDTO implements Serializable {

    private static final long serialVersionUID = 3301998863383386001L;

    /** 角色名称,not null */
    private String name;

    /** 角色编码 */
    private String code;

    /** 是否为系统初始数据(0-否, 1-是) */
    private Byte nativeState;

    /**
     * 是否设置过菜单权限(0-否, 1-是)
     */
    private Byte hasSettingMenu;

    /**
     * 是否设置过页面权限(0-否, 1-是)
     */
    private Byte getHasSettingPage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Byte getNativeState() {
        return nativeState;
    }

    public void setNativeState(Byte nativeState) {
        this.nativeState = nativeState;
    }

    public Byte getHasSettingMenu() {
        return hasSettingMenu;
    }

    public void setHasSettingMenu(Byte hasSettingMenu) {
        this.hasSettingMenu = hasSettingMenu;
    }

    public Byte getGetHasSettingPage() {
        return getHasSettingPage;
    }

    public void setGetHasSettingPage(Byte getHasSettingPage) {
        this.getHasSettingPage = getHasSettingPage;
    }
}
