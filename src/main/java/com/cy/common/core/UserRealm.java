package com.cy.common.core;

import com.cy.common.constant.Constants;
import com.cy.common.emun.ByteBooleanEnum;
import com.cy.dao.system.RoleInfoDAO;
import com.cy.dao.system.UserInfoDAO;
import com.cy.entity.system.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by zxj on 2017/2/17.
 * AuthorizingRealm授权类继承了AuthenticatingRealm认证类
 * 所以这里实行AuthorizingRealm授权类本身的授权方法doGetAuthorizationInfo
 * 和继承自AuthenticatingRealm认证类的认证方法doGetAuthenticationInfo
 */
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserInfoDAO userInfoDAO;

    @Resource
    private RoleInfoDAO roleInfoDAO;

    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Long currentUserId = (Long) SecurityUtils.getSubject().getSession().getAttribute(Constants.CURRENT_USER_ID);
        authorizationInfo.setRoles(roleInfoDAO.getRoleCodeList(currentUserId));
        Set<String> set = new HashSet<String>();
        set.add("userInfo");
        set.add("roleInfo");
        set.add("permissionInfo");
        set.add("operationInfo");
        authorizationInfo.setStringPermissions(set);
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
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal,
                user.getPassword(),
                ByteSource.Util.bytes(user.getUserName() + user.getSalt()),
                getName());
        SecurityUtils.getSubject().getSession().setAttribute(Constants.CURRENT_USER, user);
        SecurityUtils.getSubject().getSession().setAttribute(Constants.CURRENT_USER_ID, user.getId());
        return authenticationInfo;
    }

}
