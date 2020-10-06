package com.sqli.users_service.core.exceptions;

public class DataAlreadyUsedException extends RuntimeException {
    private String message;

    public DataAlreadyUsedException(String message) {
        super();
        this.message=message;
    }

    public DataAlreadyUsedException(String message, Throwable cause) {
        super(cause);
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
