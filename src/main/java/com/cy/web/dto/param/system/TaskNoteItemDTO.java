package com.cy.web.dto.param.system;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2018/2/3.
 */
public class TaskNoteItemDTO implements Serializable {
    private static final long serialVersionUID = -5368431778442075047L;

    private Long id;

    private Date date;

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
