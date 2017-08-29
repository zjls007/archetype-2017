package com.cy.entity.system;

import java.io.Serializable;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 * t_user_group_role_ref-用户组角色关联表
 */
public class UserGroupRoleRef implements Serializable {

    private static final long serialVersionUID = -8996689151287446424L;

    /** 主键,not null */
    private Long id;

    /** 角色id,not null */
    private Long roleId;

    /** 用户组id,not null */
    private Long userGroupId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

}