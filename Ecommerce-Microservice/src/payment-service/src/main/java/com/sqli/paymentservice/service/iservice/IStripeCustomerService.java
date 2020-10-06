package com.sqli.paymentservice.service.iservice;

import com.sqli.paymentservice.dto.CustomerDTO;
import com.sqli.paymentservice.dto.CustomerCardDTO;
import com.stripe.model.Customer;

public interface IStripeCustomerService {
    public Customer createCustomer(CustomerDTO customerDTO) throws Exception;
    public Customer getCustomer(String customerId) throws Exception;
    public Customer addNewCustomerCard(String customerId, CustomerCardDTO customerCardDTO) throws Exception;
}
