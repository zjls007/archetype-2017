package com.cy.entity.system;

import java.io.Serializable;
import java.util.Date;

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

    /** 类型 参考:{@link com.cy.entity.system.enums.RolePermissionRefType},not null */
    private String type;

    /** 创建时间,not null */
    private Date createTime;

    /** 最后更新时间,not null*/
    private Date lstUpdTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLstUpdTime() {
        return lstUpdTime;
    }

    public void setLstUpdTime(Date lstUpdTime) {
        this.lstUpdTime = lstUpdTime;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getOperationInfoName() {
        return operationInfoName;
    }

    public void setOperationInfoName(String operationInfoName) {
        this.operationInfoName = operationInfoName;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }
}