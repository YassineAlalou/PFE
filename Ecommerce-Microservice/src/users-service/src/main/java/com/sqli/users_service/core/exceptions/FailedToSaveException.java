package com.sqli.users_service.core.exceptions;

public class FailedToSaveException extends RuntimeException{
    private String message;

    public FailedToSaveException(String message) {
        super();
        this.message=message;
    }

    public FailedToSaveException(String message, Throwable cause) {
        super(cause);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
