package com.cy.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2017-05-05 16:29:06.
 */
public class UserInfo implements Serializable {


    private static final long serialVersionUID = 1222715330523141490L;
    private Long id;

    /** 用户名 */
    private String userName;

    /** 密码 */
    private String password;

    /** 盐 */
    private String salt;

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
    private Date createTime;

    /** 更新时间 */
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
