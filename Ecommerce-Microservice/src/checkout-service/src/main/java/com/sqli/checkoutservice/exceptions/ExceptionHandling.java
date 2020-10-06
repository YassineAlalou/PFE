package com.sqli.checkoutservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandling {
    public ResponseEntity<ErrorResponse> CheckoutFailedExceptionHandler(CheckoutFailedException e){
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            e.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<ErrorResponse> PaymentFailedFailedExceptionHandler(PaymentFailedException e){
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.PAYMENT_REQUIRED.value(),
            e.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<ErrorResponse> PaymentFailedExceptionHandler(PlaceOrderFailedException e){
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.EXPECTATION_FAILED.value(),
            e.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<ErrorResponse> ShippingFailedExceptionHandler(ShippingFailedException e){
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.EXPECTATION_FAILED.value(),
            e.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> genericExceptionHandler(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            e.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
