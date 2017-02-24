package com.cy.core;

import com.cy.common.ByteBooleanEnum;
import com.cy.dao.UserInfoDAO;
import com.cy.entity.UserInfo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * Created by zxj on 2017/2/17.
 * AuthorizingRealm授权类继承了AuthenticatingRealm认证类
 * 所以这里实行AuthorizingRealm授权类本身的授权方法doGetAuthorizationInfo
 * 和继承自AuthenticatingRealm认证类的认证方法doGetAuthenticationInfo
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserInfoDAO userInfoDAO;

    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(null);
        authorizationInfo.setStringPermissions(null);
        return authorizationInfo;
    }

    /**
     * 认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // principal 身份, credentials 凭证
        String principal = (String) authenticationToken.getPrincipal();
        // 根据身份查找用户对象
        UserInfo user = userInfoDAO.selectByUserName(principal);
        if (user == null) {
            // 账户不存在
            throw new UnknownAccountException();
        } else if (!ByteBooleanEnum.FAILED.getCode().equals(user.getAccountLocked())) {
            // 账户被锁定
            throw new LockedAccountException();
        }
        // 密码不正确会在 SimpleAuthenticationInfo验证
        // 下面为比较明文密码
        // return new SimpleAuthenticationInfo(principal, user.getPassword(), getName());
        return new SimpleAuthenticationInfo(principal,
                new Sha256Hash(principal, authenticationToken.getCredentials()).toString(),
//                "a73fcf339640929207281fb8e038884806e2eb0840f2245694dbba1d5cc89e65",
                getName());
    }

    public static void main(String[] args) {
        System.out.println(new Md5Hash("a", "1").toString());
        System.out.println(new Sha256Hash("a", "1").toString());
    }

}
