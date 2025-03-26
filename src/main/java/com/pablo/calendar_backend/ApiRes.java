package com.pablo.calendar_backend;

public class ApiRes<T> {

    private int status;
    private String message;
    private T body;

    public ApiRes() {
    }

    public ApiRes(int status, String message, T body) {
        this.status = status;
        this.message = message;
        this.body = body;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

}
