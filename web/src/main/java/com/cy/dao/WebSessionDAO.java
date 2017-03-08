package com.cy.dao;

import com.cy.entity.WebSession;
import org.springframework.stereotype.Repository;

/**
 * Created by zxj on 2017/2/20.
 */
@Repository("webSessionDAO")
public interface WebSessionDAO {

    int insert(WebSession entity);

    int deleteBySessionId(String sessionId);

    WebSession getBySessionId(String sessionId);

    int updateBySessionId(WebSession entity);


}
