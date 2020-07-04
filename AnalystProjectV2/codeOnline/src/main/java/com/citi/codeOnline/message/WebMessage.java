package com.citi.codeOnline.message;

import java.io.Serializable;

public class WebMessage<T> implements Serializable {
    private final static long serialVersionUID = 1L;
    boolean status = false;
    T entityValue = null;
    String reason = null;

    public WebMessage(boolean status, T entityValue, String reason) {
        this.status = status;
        this.entityValue = entityValue;
        this.reason = reason;
    }

    public WebMessage(boolean status, T entityValue) {
        this.status = status;
        this.entityValue = entityValue;
    }

    public WebMessage(boolean status, String reason) {
        this.status = status;
        this.reason = reason;
    }

    public WebMessage(boolean status) {
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getEntityValue() {
        return entityValue;
    }

    public void setEntityValue(T entityValue) {
        this.entityValue = entityValue;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

