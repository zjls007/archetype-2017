package com.cy.core;

import com.cy.dao.WebSessionDAO;
import com.cy.entity.WebSession;
import com.cy.util.SerializableUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * Created by zxj on 2017/2/20.
 */
public class MysqlSessionDAO extends CachingSessionDAO {

    @Resource
    private WebSessionDAO webSessionDAO;

    /**
     * 创建
     * @param session
     * @return
     */
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);

        WebSession ws = new WebSession();
        ws.setSessionId(sessionId.toString());
        ws.setSessionSerVal(SerializableUtil.serialize(session));
        webSessionDAO.insert(ws);
        return session.getId();
    }

    /**
     * 读取
     * @param serializable
     * @return
     */
    protected Session doReadSession(Serializable serializable) {
        WebSession ws = webSessionDAO.getBySessionId(serializable.toString());
        if (ws == null) return null;
        return SerializableUtil.deserialize(ws.getSessionSerVal());
    }

    /**
     * 更新 - 每次都会更新lastAccessTime
     * @param session
     */
    protected void doUpdate(Session session) {
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return; //如果会话过期/停止 没必要再更新了
        }
        WebSession ws = new WebSession();
        ws.setSessionId(session.getId().toString());
        ws.setSessionSerVal(SerializableUtil.serialize(session));
        webSessionDAO.updateBySessionId(ws);
    }

    /**
     * 删除
     * @param session
     */
    protected void doDelete(Session session) {
        webSessionDAO.deleteBySessionId(session.getId().toString());
    }

}
