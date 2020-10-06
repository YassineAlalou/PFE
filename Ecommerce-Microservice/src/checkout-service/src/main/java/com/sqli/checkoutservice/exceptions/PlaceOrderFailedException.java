package com.sqli.checkoutservice.exceptions;

public class PlaceOrderFailedException extends RuntimeException {
    public PlaceOrderFailedException() {
    }

    public PlaceOrderFailedException(String message) {
        super(message);
    }

    public PlaceOrderFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlaceOrderFailedException(Throwable cause) {
        super(cause);
    }
}
