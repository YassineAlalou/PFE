package com.sqli.checkoutservice.dtos.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private double amount;
    private String currency;
    private CustomerCardDTO cardInfo;
    private CustomerDTO customerInfo;
}
