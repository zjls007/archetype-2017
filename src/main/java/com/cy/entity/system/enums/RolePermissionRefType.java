package com.cy.entity.system.enums;

import org.springframework.util.StringUtils;

/**
 * t_role_permission_ref.type 类型（menu-菜单、page-页面元素）,not null
 * Created by zxj on 2017/11/10.
 */
public enum RolePermissionRefType {

    MENU("menu", "菜单"),
    PAGE("page", "页面元素"),
    ;

    private String code;
    private String name;

    public static boolean contains(String code) {
        if (!StringUtils.isEmpty(code)) {
            for (RolePermissionRefType t : RolePermissionRefType.values()) {
                if (t.getCode().equals(code)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static RolePermissionRefType convert(String code) {
        if (!StringUtils.isEmpty(code)) {
            for (RolePermissionRefType t : RolePermissionRefType.values()) {
                if (t.getCode().equals(code)) {
                    return t;
                }
            }
        }
        return null;
    }

    RolePermissionRefType(String code, String name) {
        this.code = code;
        this.name = name;
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
