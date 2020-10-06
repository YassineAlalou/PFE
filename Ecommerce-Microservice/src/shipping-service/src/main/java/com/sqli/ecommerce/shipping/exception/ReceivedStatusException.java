package com.sqli.ecommerce.shipping.exception;

public class ReceivedStatusException extends RuntimeException {

    public ReceivedStatusException() {
    }

    public ReceivedStatusException(String message) {
        super(message);
    }

    public ReceivedStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReceivedStatusException(Throwable cause) {
        super(cause);
    }
}
