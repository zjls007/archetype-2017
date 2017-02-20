package com.cy.entity;

import org.apache.ibatis.type.Alias;

/**
 * Created by zxj on 2017/2/20.
 */
@Alias("webSession")
public class WebSession {

    private Long id;

    private String sessionId;

    private String sessionSerVal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionSerVal() {
        return sessionSerVal;
    }

    public void setSessionSerVal(String sessionSerVal) {
        this.sessionSerVal = sessionSerVal;
    }
}
