package com.cy.common.core;

import com.cy.common.util.SystemURL;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * Created by zxj on 2017/3/1.
 */
public class RedisSessionDAO extends AbstractSessionDAO {

    @Autowired
    private RedisTemplate<String, Session> redisTemplate;

    private static final String KEY_PREFIX = "sessionId:";

    /**
     * 创建
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        String key = KEY_PREFIX + sessionId.toString();
        // 新创建的session存入redis一分钟
        redisTemplate.opsForValue().set(key, session, 5, TimeUnit.MINUTES);
        System.out.println("创建" + session);
        return session.getId();
    }

    /**
     * 读取
     * @param serializable
     * @return
     */
    @Override
    protected Session doReadSession(Serializable serializable) {
        HttpServletRequest request =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        System.out.println(request.getRequestURL());
        System.out.println("读取" + serializable);
        String key = KEY_PREFIX + serializable.toString();
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        HttpServletRequest request =  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString();
        if (SystemURL.isValidUrl(url)) {
            System.out.println("更新" + session);
            if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
                return; //如果会话过期/停止 没必要再更新了
            }
            String key = KEY_PREFIX + session.getId().toString();
            Integer unit = 5;
            if (SecurityUtils.getSubject().isAuthenticated()) {
                unit = 30;
            }
            redisTemplate.opsForValue().set(key, session, unit, TimeUnit.MINUTES);
        } else {
            System.out.println("不更新的url: " + url);
        }
    }

    @Override
    public void delete(Session session) {
        System.out.println("删除" + session);
        String key = KEY_PREFIX + session.getId().toString();
        redisTemplate.delete(key);
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return null;
    }
}
