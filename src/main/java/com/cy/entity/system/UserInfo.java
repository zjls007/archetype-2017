package com.cy.entity.system;

import com.cy.common.annotation.ParamValid;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2017-12-06 14:19:19.
 * t_user_info-用户表
 */
@ParamValid
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -549565203291010723L;

    /** ,not null */
    private Long id;

    /** 用户名,not null */
    @NotBlank(message="用户名不能为空!")
    @Length(min = 2, max = 15, message = "用户名长度必须在{min}-{max}位之间!")
    private String userName;

    /** 手机号 */
    private String mobilePhoneNumber;

    /** 邮箱 */
    @Email(message = "邮箱格式不正确!")
    private String email;

    /** 电话号码 */
    private String telNo;

    /** 密码 */
    private String password;

    /** 盐 */
    private String salt;

    /** 登录系统显示的名称,not null */
    @NotBlank(message="姓名不能为空!")
    @Length(min = 2, max = 15, message = "用户名长度必须在{min}-{max}位之间!")
    private String fullName;

    /** 性别 参考:{@link com.cy.entity.system.enums.UserInfoSex} */
    private String sex;

    /** 登录次数 */
    private Long signNo;

    /** 出生年月 */
    private Date birthday;

    /** 最后登录ip */
    private String lastLoginIp;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastLoginTime;

    /** 账户是否锁定 0 未锁定 1锁定 */
    private Byte accountLocked;

    /** 是否为系统初始数据 参考:{@link com.cy.entity.system.enums.ByteBooleanEnum} */
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getSignNo() {
        return signNo;
    }

    public void setSignNo(Long signNo) {
        this.signNo = signNo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Byte getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Byte accountLocked) {
        this.accountLocked = accountLocked;
    }

    public Byte getNativeState() {
        return nativeState;
    }

    public void setNativeState(Byte nativeState) {
        this.nativeState = nativeState;
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