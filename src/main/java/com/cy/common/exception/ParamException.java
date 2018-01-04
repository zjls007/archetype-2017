package com.cy.common.exception;

import com.cy.common.Response;
import com.cy.common.constant.ResponseStatus;

/**
 * Created by hyl on 2017/3/5.
 */
public class ParamException extends RuntimeException {

    private Response response;

    public ParamException() {
        this.response = new Response(ResponseStatus.PARAM_ERROR);
    }

    public ParamException(String message) {
        this.response = new Response(ResponseStatus.PARAM_ERROR, message);
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
