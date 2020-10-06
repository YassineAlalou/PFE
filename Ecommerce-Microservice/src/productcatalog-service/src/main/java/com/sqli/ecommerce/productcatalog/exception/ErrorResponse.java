package com.sqli.ecommerce.productcatalog.exception;

public class ErrorResponse {

    int status;
    String message;
    long timestamps;

    public ErrorResponse() {
    }

    public ErrorResponse(int status, String message, long timestamps) {
        this.status = status;
        this.message = message;
        this.timestamps = timestamps;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamps() {
        return timestamps;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTimestamps(int timestamps) {
        this.timestamps = timestamps;
    }
}
