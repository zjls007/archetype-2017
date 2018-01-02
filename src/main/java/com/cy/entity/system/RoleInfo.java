package com.cy.entity.system;

import com.cy.common.annotation.ParamValid;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 * t_role_info-角色信息表
 */
@ParamValid
public class RoleInfo implements Serializable {

    private static final long serialVersionUID = 1468909215847375601L;

    /** 主键,not null */
    private Long id;

    /** 角色名称,not null */
    @NotNull(message="角色名称为空")
    @Length(min = 2, max = 15, message = "角色名称长度必须在{min}-{max}位之间")
    private String name;

    /** 角色编码 */
    @NotNull(message="角色编码为空")
    @Length(min = 2, max = 15, message = "角色编码长度必须在{min}-{max}位之间")
    private String code;

    /** 是否为系统初始数据(0-否, 1-是) */
    private Byte nativeState;

    /** 创建时间,not null */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 最后更新时间,not null */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lstUpdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Byte getNativeState() {
        return nativeState;
    }

    public void setNativeState(Byte nativeState) {
        this.nativeState = nativeState;
    }
}