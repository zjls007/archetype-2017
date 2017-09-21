package com.cy.web.dto.param.system;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2017/9/21.
 */
public class UserInfoQueryDTO implements Serializable {

    private static final long serialVersionUID = 8057840530826303458L;

    /** 用户名 */
    private String userName;

    /** 真实姓名 */
    private String fullName;

    /** 电话号码 */
    private String telNo;

    /** 手机号 */
    private String mobileNo;

    /** 账户是否锁定 0 未锁定 1锁定 */
    private Byte accountLocked;

    /** 创建时间开始 */
    private Date createTimeBegin;

    /** 创建时间结束 */
    private Date createTimeEnd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Byte getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Byte accountLocked) {
        this.accountLocked = accountLocked;
    }

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
