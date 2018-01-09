package com.cy.web.vo;

import java.io.Serializable;

/**
 * Created by zxj on 2018/1/9.
 */
public class Select2ItemVO implements Serializable {

    private static final long serialVersionUID = -7848993231415264674L;

    private String id;

    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
