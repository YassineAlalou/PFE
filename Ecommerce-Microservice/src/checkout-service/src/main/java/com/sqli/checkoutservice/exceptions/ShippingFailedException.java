package com.sqli.checkoutservice.exceptions;

public class ShippingFailedException extends RuntimeException{

    public ShippingFailedException() {
    }

    public ShippingFailedException(String message) {
        super(message);
    }

    public ShippingFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ShippingFailedException(Throwable cause) {
        super(cause);
    }
}
