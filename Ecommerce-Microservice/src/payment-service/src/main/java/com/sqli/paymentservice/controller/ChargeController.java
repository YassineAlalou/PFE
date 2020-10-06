package com.sqli.paymentservice.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqli.paymentservice.dto.ChargeCustomerCardDto;
import com.sqli.paymentservice.dto.ChargeNewCardDTO;
import com.sqli.paymentservice.service.implservice.StripeChargeService;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;

@Log
@RestController
@RequestMapping("/payment")
@Api(value = "Payment Charge API", description = "Operations to ChargeController")
public class ChargeController {

    private final StripeChargeService stripeChargeService;

    @Inject
    public ChargeController(StripeChargeService stripeChargeService) {
        this.stripeChargeService = stripeChargeService;
    }

    @PostMapping(value = "/new-card/charge",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Charge no customer card")
    public Charge chargeNewCard(@RequestBody ChargeNewCardDTO chargeNewCardDTO) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        return stripeChargeService.chargeNewCard(chargeNewCardDTO);
    }

    @PostMapping("/customer-card/charge")
    @ApiOperation(value = "Charge customer card")
    public Charge chargeCustomerCard(@RequestBody ChargeCustomerCardDto chargeCustomerCardDto) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        return stripeChargeService.chargeCustomerCard(chargeCustomerCardDto);
    }

}
