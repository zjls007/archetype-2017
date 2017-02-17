package com.cy.entity;

import org.apache.ibatis.type.Alias;

/**
 * Created by zxj on 2017/2/7.
 */
@Alias("userInfo")
public class UserInfo {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 账户是否锁定 0 未锁定 1锁定
     */
    private Byte accountLocked;

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

    public Byte getAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(Byte accountLocked) {
        this.accountLocked = accountLocked;
    }
}

