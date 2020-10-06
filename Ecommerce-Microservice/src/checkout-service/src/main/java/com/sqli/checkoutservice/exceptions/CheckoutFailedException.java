package com.sqli.checkoutservice.exceptions;


public class CheckoutFailedException extends Exception{

    public CheckoutFailedException() {
    }

    public CheckoutFailedException(String message) {
        super(message);
    }

    public CheckoutFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}
