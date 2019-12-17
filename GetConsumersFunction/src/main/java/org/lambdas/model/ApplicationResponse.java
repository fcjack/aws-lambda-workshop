package org.lambdas.model;

import java.util.List;

public class ApplicationResponse {

    private String message;
    private List<Consumer> consumers;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Consumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(List<Consumer> consumers) {
        this.consumers = consumers;
    }
}
