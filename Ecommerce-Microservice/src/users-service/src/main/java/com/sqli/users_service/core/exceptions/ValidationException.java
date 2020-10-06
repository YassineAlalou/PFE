package com.sqli.users_service.core.exceptions;

public class ValidationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;

    public ValidationException(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

}
