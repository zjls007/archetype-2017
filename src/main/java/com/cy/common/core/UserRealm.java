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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
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

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Long currentUserId = (Long) SecurityUtils.getSubject().getSession().getAttribute(Constants.CURRENT_USER_ID);

        Set<String> roles = stringRedisTemplate.opsForSet().members(String.format("roles-%s", currentUserId));
        if (roles == null || roles.isEmpty()) {
            roles = roleInfoDAO.getRoleCodeList(currentUserId);
            if (roles != null && !roles.isEmpty()) {
                stringRedisTemplate.opsForSet().add(String.format("roles-%s", currentUserId), roles.toArray(new String[roles.size()]));
            }
        }
        authorizationInfo.setRoles(roles);

        Set<String> perms = stringRedisTemplate.opsForSet().members(String.format("perms-%s", currentUserId));
        if (perms == null || perms.isEmpty()) {
            perms = roleInfoDAO.getPermByUser(currentUserId);
            if (perms != null && !perms.isEmpty()) {
                stringRedisTemplate.opsForSet().add(String.format("perms-%s", currentUserId), perms.toArray(new String[perms.size()]));
            }
        }
        authorizationInfo.setStringPermissions(perms);
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
        UserInfo user = userInfoDAO.getByUserName(principal);
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
        userInfoDAO.updateLastLoginTime(user.getId());
        SecurityUtils.getSubject().getSession().setAttribute(Constants.CURRENT_USER, user);
        SecurityUtils.getSubject().getSession().setAttribute(Constants.CURRENT_USER_ID, user.getId());
        return authenticationInfo;
    }

}
