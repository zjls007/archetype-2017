package com.cy.common.exception;

import com.cy.common.Response;
import com.cy.common.constant.ResponseStatus;

/**
 * Created by zxj on 2018/1/4.
 */
public class ValidException extends RuntimeException {

    private Response response;

    public ValidException() {
        this.response = new Response(ResponseStatus.VALID_ERROR);
    }

    public ValidException(String message) {
        this.response = new Response(ResponseStatus.VALID_ERROR, message);
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
