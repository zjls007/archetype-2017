package com.cy.entity.system;

import java.io.Serializable;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 * t_permission_operation_ref-权限-操作关联表
 */
public class PermissionOperationRef implements Serializable {

    private static final long serialVersionUID = 5709922916976912223L;

    /** 主键,not null */
    private Long id;

    /** 权限id,not null */
    private Long permissionId;

    /** 操作id,not null */
    private Long operationId;

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

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

}