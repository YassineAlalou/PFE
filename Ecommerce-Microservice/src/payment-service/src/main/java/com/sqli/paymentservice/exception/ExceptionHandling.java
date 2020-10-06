package com.sqli.paymentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stripe.exception.StripeException;

@ControllerAdvice
public class ExceptionHandling {
    @ResponseBody
    @ExceptionHandler(value = StripeException.class)
    public ResponseEntity<ErrorResponse> StripeExceptionHandler(StripeException e){
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            e.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.PAYMENT_REQUIRED);
    }

    @ResponseBody
    @ExceptionHandler(value = CustomerAlreadyExistCardException.class)
    public ResponseEntity<ErrorResponse> CustomerAlreadyExistCardExceptionHandler(CustomerAlreadyExistCardException e){
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.CONFLICT.value(),
            e.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.PAYMENT_REQUIRED);
    }


    public ResponseEntity<ErrorResponse> genericExceptionHandler(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            e.getMessage(),
            System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}