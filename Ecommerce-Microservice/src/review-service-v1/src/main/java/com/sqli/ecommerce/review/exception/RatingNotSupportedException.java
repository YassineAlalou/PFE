package com.sqli.ecommerce.review.exception;

public class RatingNotSupportedException extends RuntimeException {
    public RatingNotSupportedException() {
    }

    public RatingNotSupportedException(String message) {
        super(message);
    }

    public RatingNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RatingNotSupportedException(Throwable cause) {
        super(cause);
    }
}