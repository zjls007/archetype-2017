package com.cy.web.dto.param;

import com.cy.common.annotation.ParamValid;
import com.cy.entity.UserInfo;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by zxj on 2017/5/8.
 */
@ParamValid
public class RegistParamDTO implements Serializable {

    private static final long serialVersionUID = -5990461383597289254L;

    /** 用户名 */
    @NotNull(message="用户名不能为空")
    private String userName;

    /** 密码 */
    @NotNull(message="密码不能为空")
    private String password;

    /** 真实姓名 */
    private String fullName;

    /** 电话号码 */
    private String telNo;

    /** 手机号 */
    private String mobileNo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
}
