package com.cy.entity.system;

import java.io.Serializable;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 * t_role_permission_ref-角色-权限关联表
 */
public class RolePermissionRef implements Serializable {

    private static final long serialVersionUID = -5163230561878701805L;

    /** 主键,not null */
    private Long id;

    /** 角色id,not null */
    private Long roleId;

    /** 权限id,not null */
    private Long permissionId;

    /** 权限操作id,多个用逗号分割 */
    private String operationInfoId;

    /** 权限操作code,多个用逗号分割 */
    private String operationInfoCode;

    /** 类型（menu-菜单、page-页面元素）,not null */
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getOperationInfoId() {
        return operationInfoId;
    }

    public void setOperationInfoId(String operationInfoId) {
        this.operationInfoId = operationInfoId;
    }

    public String getOperationInfoCode() {
        return operationInfoCode;
    }

    public void setOperationInfoCode(String operationInfoCode) {
        this.operationInfoCode = operationInfoCode;
    }
}