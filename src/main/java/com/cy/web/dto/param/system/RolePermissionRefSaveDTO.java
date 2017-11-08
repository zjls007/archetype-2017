package com.cy.web.dto.param.system;

import java.io.Serializable;

/**
 * Created by zxj on 2017/11/8.
 */
public class RolePermissionRefSaveDTO implements Serializable {

    private static final long serialVersionUID = -455967592436520573L;

    /** 角色id,not null */
    private Long roleId;

    /** 权限id,not null */
    private Long permissionId;

    /** 权限code,not null */
    private String permissionCode;

    /** 权限名称,not null */
    private String permissionName;

    /** 权限操作id,多个用逗号分割 */
    private String operationInfoId;

    /** 权限操作code,多个用逗号分割 */
    private String operationInfoCode;

    /** 权限操作名称,多个用逗号分割 */
    private String operationInfoName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
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

    public String getOperationInfoName() {
        return operationInfoName;
    }

    public void setOperationInfoName(String operationInfoName) {
        this.operationInfoName = operationInfoName;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
}
