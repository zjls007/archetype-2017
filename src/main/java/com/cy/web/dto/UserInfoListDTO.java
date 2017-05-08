package com.cy.web.dto;

import com.cy.entity.UserInfo;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2017/5/8.
 */
public class UserInfoListDTO implements Serializable {

    private static final long serialVersionUID = 5531963892055200807L;

    private Long id;

    /** 用户名 */
    private String userName;

    /** 真实姓名 */
    private String fullName;

    /** 电话号码 */
    private String telNo;

    /** 手机号 */
    private String mobileNo;

    /** 登录次数 */
    private Long signNo;

    /** 账户是否锁定 0 未锁定 1锁定 */
    private Byte accountLocked;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getSignNo() {
        return signNo;
    }

    public void setSignNo(Long signNo) {
        this.signNo = signNo;
    }

    public Byte getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Byte accountLocked) {
        this.accountLocked = accountLocked;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
