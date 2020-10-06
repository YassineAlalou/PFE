package com.sqli.paymentservice.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sqli.paymentservice.dto.CustomerCardDTO;
import com.sqli.paymentservice.dto.CustomerDTO;
import com.sqli.paymentservice.service.implservice.StripeCustomerService;
import com.stripe.model.Customer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/customers")
@Api(value = "Customers API", description = "Customer Operations")
public class CustomerController {

    private final StripeCustomerService stripeCustomerService;
    @Inject
    public CustomerController(StripeCustomerService stripeCustomerService) {
        this.stripeCustomerService = stripeCustomerService;
    }

    @PostMapping("/")
    @ApiOperation(value = "create new Customer")
    public Customer createCustomer(@RequestBody CustomerDTO customerDTO) throws Exception {
        return stripeCustomerService.createCustomer(customerDTO);
    }

    @GetMapping("/{customerId}")
    @ApiOperation(value = "retrieve customer")
    public Customer getCustomer(@PathVariable("customerId") String customerId) throws Exception {
        return stripeCustomerService.getCustomer(customerId);
    }

    @PostMapping("/{customerId}/card")
    @ApiOperation(value = "create New Customer Card")
    public Customer createCard(@PathVariable String customerId, @RequestBody CustomerCardDTO customerCardDTO) throws Exception {
        return stripeCustomerService.addNewCustomerCard(customerId,customerCardDTO);
    }

}
