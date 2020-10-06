package com.sqli.paymentservice.dto;


import com.sqli.paymentservice.enums.CURRENCY;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChargeCustomerCardDto {
    private String customerId;
    private long amount;
    private CURRENCY currency;
}
