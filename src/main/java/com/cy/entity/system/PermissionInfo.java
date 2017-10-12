package com.cy.entity.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2017-08-29 10:32:27.
 * t_permission_info-权限表
 */
public class PermissionInfo implements Serializable {

    private static final long serialVersionUID = 974874450734662110L;

    /** 主键,not null */
    private Long id;

    /** 关联的数据id,not null */
    private Long dataId;

    /** 权限名称,not null */
    @NotNull(message="权限名称为空")
    @Length(min = 2, max = 15, message = "权限名称长度必须在{min}-{max}位之间")
    private String name;

    /** 权限code,not null */
    @NotNull(message="权限编码为空")
    @Length(min = 2, max = 15, message = "权限编码长度必须在{min}-{max}位之间")
    private String code;

    /** ,not null */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** ,not null */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lstUpdTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
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

}