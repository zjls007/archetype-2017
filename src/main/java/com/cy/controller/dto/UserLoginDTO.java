package com.cy.controller.dto;

import java.io.Serializable;

/**
 * Created by zxj on 2017/2/22.
 */
public class UserLoginDTO implements Serializable {

    private static final long serialVersionUID = 7711385153569949368L;

    /**
     * 身份
     */
    private String principal;

    /**
     * 凭证
     */
    private String credentials;

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }
}
