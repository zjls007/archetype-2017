package com.cy.web.dto.param.system;

import com.cy.entity.system.UserInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2017/9/21.
 */
public class UserInfoFrontQueryDTO extends UserInfo {

    private static final long serialVersionUID = 830946188858821586L;

    /** 注册时间开始 */
    private Date createTimeBegin;

    /** 注册时间结束 */
    private Date createTimeEnd;

    public Date getCreateTimeBegin() {
        return createTimeBegin;
    }

    public void setCreateTimeBegin(Date createTimeBegin) {
        this.createTimeBegin = createTimeBegin;
    }

    public Date getCreateTimeEnd() {
        return createTimeEnd;
    }

    public void setCreateTimeEnd(Date createTimeEnd) {
        this.createTimeEnd = createTimeEnd;
    }
}
