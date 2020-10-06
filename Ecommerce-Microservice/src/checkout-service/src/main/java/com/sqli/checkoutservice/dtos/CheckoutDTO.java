package com.sqli.checkoutservice.dtos;

import com.sqli.checkoutservice.dtos.cart_orders.CartOrderDTO;
import com.sqli.checkoutservice.dtos.payment.CustomerDTO;
import com.sqli.checkoutservice.dtos.payment.PaymentDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutDTO {
    private CartOrderDTO cart;
    private PaymentDTO payment;
    private String address;
}
