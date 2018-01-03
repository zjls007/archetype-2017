package com.cy.web.dto.param.system;

import com.cy.common.annotation.ParamValid;
import com.sun.tools.corba.se.idl.StringGen;
import org.apache.ibatis.annotations.Param;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by zxj on 2018/1/3.
 */
@ParamValid
public class ModifyPwdDTO implements Serializable {

    private static final long serialVersionUID = -8506196358257834365L;

    @NotNull
    private String oldPassword;

    @NotNull
    @Length(min = 6, max = 20, message = "密码长度必须在{min}-{max}位之间")
    private String password;

    @NotNull
    private String passwordConfirm;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
