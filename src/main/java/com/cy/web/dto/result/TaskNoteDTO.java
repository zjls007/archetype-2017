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

    /** 日期,not null */
    private Date date;

    /** 备注,not null */
    private String remark;

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
}
