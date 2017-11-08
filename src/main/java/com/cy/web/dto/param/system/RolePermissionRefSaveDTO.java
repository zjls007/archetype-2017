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

    /** 权限操作id,多个用逗号分割 */
    private String operationInfoId;

    /** 权限操作code,多个用逗号分割 */
    private String operationInfoCode;

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
}
