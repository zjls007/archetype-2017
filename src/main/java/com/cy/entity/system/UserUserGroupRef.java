package com.cy.entity.system;

import java.io.Serializable;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 * t_user_user_group_ref-用户用户组关联表
 */
public class UserUserGroupRef implements Serializable {

    private static final long serialVersionUID = -8469204682010176264L;

    /** 主键,not null */
    private Long id;

    /** 用户id,not null */
    private Long userId;

    /** 用户组id,not null */
    private Long userGroupId;

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

    public Long getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Long userGroupId) {
        this.userGroupId = userGroupId;
    }

}