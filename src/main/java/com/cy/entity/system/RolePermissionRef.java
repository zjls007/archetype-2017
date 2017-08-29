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
    private Long roldId;

    /** 权限id,not null */
    private Long permissionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoldId() {
        return roldId;
    }

    public void setRoldId(Long roldId) {
        this.roldId = roldId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

}