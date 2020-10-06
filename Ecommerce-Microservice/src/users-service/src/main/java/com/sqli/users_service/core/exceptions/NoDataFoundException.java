package com.sqli.users_service.core.exceptions;

public class NoDataFoundException extends RuntimeException {
    private String message;

    public NoDataFoundException(String message) {
        super();
        this.message=message;
    }

    public NoDataFoundException(String message, Throwable cause) {
        super(cause);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
