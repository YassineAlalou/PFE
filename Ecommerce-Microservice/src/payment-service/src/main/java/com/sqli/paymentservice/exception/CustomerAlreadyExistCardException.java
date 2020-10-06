package com.sqli.paymentservice.exception;

public class CustomerAlreadyExistCardException extends RuntimeException{
    public CustomerAlreadyExistCardException() {
    }

    public CustomerAlreadyExistCardException(String message) {
        super(message);
    }

    public CustomerAlreadyExistCardException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerAlreadyExistCardException(Throwable cause) {
        super(cause);
    }
}
