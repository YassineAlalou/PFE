package com.sqli.paymentservice.service.implservice;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sqli.paymentservice.dto.CustomerDTO;
import com.sqli.paymentservice.dto.CustomerCardDTO;
import com.sqli.paymentservice.exception.CustomerAlreadyExistCardException;
import com.sqli.paymentservice.service.iservice.IStripeCustomerService;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Card;
import com.stripe.model.Customer;
import com.stripe.model.ExternalAccount;
import com.stripe.model.ExternalAccountCollection;
import com.stripe.model.Token;

@Service
public class StripeCustomerService implements IStripeCustomerService {

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Customer createCustomer(CustomerDTO customerDTO) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("name", customerDTO.getName());
        customerParams.put("email", customerDTO.getEmail());
        customerParams.put("phone", customerDTO.getPhone());
        customerParams.put("description", "new customer Account was added");
        return Customer.create(customerParams);
    }

    public Customer getCustomer(String customerId) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        return Customer.retrieve(customerId);
    }

    @Override
    public Customer addNewCustomerCard(String customerId, CustomerCardDTO customerCardDTO) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        Customer customer=this.getCustomer(customerId);
        Map<String, Object> cardParams = new HashMap<String, Object>();
        cardParams.put("number",customerCardDTO.getCardNumber());
        cardParams.put("exp_month",customerCardDTO.getExp_month());
        cardParams.put("exp_year",customerCardDTO.getExp_year());
        cardParams.put("cvc",customerCardDTO.getCvc());

        Map<String, Object> tokenParams = new HashMap<String, Object>();
        tokenParams.put("card",cardParams);
        Token token=Token.create(tokenParams);
        if (checkSameCardCreatedMultipleTime(token,customer.getId())) {
            Map<String, Object> source = new HashMap<String, Object>();
            source.put("source", token.getId());
            customer.getSources().create(source);
        } else {
            throw new CustomerAlreadyExistCardException("Customer already has a card with same identifiers");
        }
        return  customer;
    }

    public Boolean checkSameCardCreatedMultipleTime(Token token,String customerId) throws CardException, APIException, AuthenticationException, InvalidRequestException, APIConnectionException {
        Customer customer=this.getCustomer(customerId);
        ExternalAccountCollection allCardDetails=customer.getSources();
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        boolean cardIsNotExist=true;
        for (ExternalAccount cardDetails:allCardDetails.getData()) {
            String a=cardDetails.toJson();
            Card card=gson.fromJson(a,Card.class);
            if(card.getFingerprint().equals(token.getCard().getFingerprint()))
               cardIsNotExist=false;
        }
        return cardIsNotExist;
    }

}
