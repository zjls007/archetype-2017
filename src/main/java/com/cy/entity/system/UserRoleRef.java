package com.cy.entity.system;

import java.io.Serializable;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 * t_user_role_ref-用户角色关联表
 */
public class UserRoleRef implements Serializable {

    private static final long serialVersionUID = -8994097896205168933L;

    /** 主键,not null */
    private Long id;

    /** 用于id,not null */
    private Long userId;

    /** 角色id,not null */
    private Long roleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}