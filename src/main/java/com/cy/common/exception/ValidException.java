package com.cy.common.exception;

import com.github.pagehelper.PageHelper;

/**
 * Created by hyl on 2017/3/5.
 */
public class ValidException extends RuntimeException {

    public ValidException() {
        super();
    }

    public ValidException(String msg) {
        super(msg);

    }

}
