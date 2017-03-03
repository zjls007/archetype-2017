package com.cy.core;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by zxj on 2017/3/1.
 */
public class RedisSessionDAO extends CachingSessionDAO {

    @Autowired
    private RedisTemplate<String, Session> redisTemplate;

    private static final String KEY_PREFIX = "sessionId:";

    /**
     * 创建
     * @param session
     * @return
     */
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
    protected Session doReadSession(Serializable serializable) {
        System.out.println("读取" + serializable);
        String key = KEY_PREFIX + serializable.toString();
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 更新 - 每次都会更新lastAccessTime
     * @param session
     */
    protected void doUpdate(Session session) {
        System.out.println("更新" + session);
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return; //如果会话过期/停止 没必要再更新了
        }
//        WebSession ws = new WebSession();
//        ws.setSessionId(session.getId().toString());
//        ws.setSessionSerVal(SerializableUtil.serialize(session));
//        webSessionDAO.updateBySessionId(ws);
    }

    /**
     * 删除
     * @param session
     */
    protected void doDelete(Session session) {
        System.out.println("删除" + session);
        String key = KEY_PREFIX + session.getId().toString();
        redisTemplate.delete(key);
    }

}
