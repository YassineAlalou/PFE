package com.sqli.checkoutservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqli.checkoutservice.dtos.CheckoutDTO;
import com.sqli.checkoutservice.exceptions.CheckoutFailedException;
import com.sqli.checkoutservice.facades.CheckoutFacade;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Checkout resource API")
public class CheckoutController {

    private final CheckoutFacade checkoutFacade;

    @Autowired
    public CheckoutController(CheckoutFacade checkoutFacade) {
        this.checkoutFacade = checkoutFacade;
    }

    @PostMapping("/checkout")
    @ApiOperation(value = "process Checkout Order")
    public ResponseEntity<Void> checkout(@RequestBody CheckoutDTO checkoutDTO) throws CheckoutFailedException {
       boolean checkoutResult=checkoutFacade.checkout(checkoutDTO);
        if (!checkoutResult){
            throw new CheckoutFailedException("Failed to checkout your Order");
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
