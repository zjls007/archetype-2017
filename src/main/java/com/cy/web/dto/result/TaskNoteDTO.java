package com.cy.web.dto.result;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2018/2/2.
 */
public class TaskNoteDTO implements Serializable {

    private static final long serialVersionUID = 2019633780040832927L;

    /** 主键,not null */
    private Long id;

    private String md5;

    /** 日期,not null */
    private Date date;

    /** 备注,not null */
    private String remark;

    /** edit:显示文本框, text:显示文本内容 */
    private String type;

    /** 小于等于今天 */
    private Boolean lessEqualNow = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getLessEqualNow() {
        return lessEqualNow;
    }

    public void setLessEqualNow(Boolean lessEqualNow) {
        this.lessEqualNow = lessEqualNow;
    }
}
