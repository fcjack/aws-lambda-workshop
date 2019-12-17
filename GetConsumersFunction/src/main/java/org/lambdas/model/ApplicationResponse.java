package org.lambdas.model;

import java.util.List;

public class ApplicationResponse {

    private Integer statusCode;
    private String body;

    public ApplicationResponse(Integer statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
