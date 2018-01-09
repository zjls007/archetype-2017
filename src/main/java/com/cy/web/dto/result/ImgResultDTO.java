package com.cy.web.dto.result;

import java.io.Serializable;

/**
 * Created by zxj on 2018/1/9.
 */
public class ImgResultDTO implements Serializable {

    private static final long serialVersionUID = 7498903032166300841L;

    private String id;

    private String p1;

    private String p2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }
}
