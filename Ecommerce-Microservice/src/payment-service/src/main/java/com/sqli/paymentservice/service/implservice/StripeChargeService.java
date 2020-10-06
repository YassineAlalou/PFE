package com.sqli.paymentservice.service.implservice;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sqli.paymentservice.dto.CustomerDTO;
import com.sqli.paymentservice.dto.ChargeCustomerCardDto;
import com.sqli.paymentservice.dto.ChargeNewCardDTO;
import com.sqli.paymentservice.enums.CURRENCY;
import com.sqli.paymentservice.service.iservice.IStripeChargeService;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;

@Service
public class StripeChargeService implements IStripeChargeService {

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    private final StripeCustomerService stripeCustomerService;

    @Inject
    public StripeChargeService(StripeCustomerService stripeCustomerService) {
        this.stripeCustomerService = stripeCustomerService;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Charge chargeNewCard(ChargeNewCardDTO chargeNewCardDTO) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        System.out.println(chargeNewCardDTO.toString());
        Customer customer=stripeCustomerService.createCustomer(chargeNewCardDTO.getCustomerInfo());
        customer=stripeCustomerService.addNewCustomerCard(customer.getId(),chargeNewCardDTO.getCardInfo());
        System.out.println(customer.toJson());
        return getCharge(customer, chargeNewCardDTO.getAmount(), chargeNewCardDTO.getCurrency());
    }


    public Charge chargeCustomerCard(ChargeCustomerCardDto chargeCustomerCardDto) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        Customer customer= stripeCustomerService.getCustomer(chargeCustomerCardDto.getCustomerId());
        return getCharge(customer, chargeCustomerCardDto.getAmount(), chargeCustomerCardDto.getCurrency());
    }

    private Charge getCharge(Customer customer, long amount, CURRENCY currency) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", amount);
        chargeParams.put("currency", currency);
        chargeParams.put("customer", customer.getId());
        return Charge.create(chargeParams);
    }

}
