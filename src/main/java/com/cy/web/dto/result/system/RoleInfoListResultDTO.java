package com.cy.web.dto.result.system;

import com.cy.entity.system.RoleInfo;

import java.io.Serializable;

/**
 * Created by zxj on 2017/11/9.
 */
public class RoleInfoListResultDTO extends RoleInfo implements Serializable {

    private static final long serialVersionUID = 3926694984885977747L;

    /**
     * 是否设置过菜单权限
     */
    private String hasSettingMenu;

    /**
     * 是否设置过页面权限
     */
    private String getHasSettingPage;

    public String getHasSettingMenu() {
        return hasSettingMenu;
    }

    public void setHasSettingMenu(String hasSettingMenu) {
        this.hasSettingMenu = hasSettingMenu;
    }

    public String getGetHasSettingPage() {
        return getHasSettingPage;
    }

    public void setGetHasSettingPage(String getHasSettingPage) {
        this.getHasSettingPage = getHasSettingPage;
    }
}
