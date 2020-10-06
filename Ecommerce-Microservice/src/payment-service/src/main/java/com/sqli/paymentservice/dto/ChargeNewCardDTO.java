package com.sqli.paymentservice.dto;


import com.sqli.paymentservice.enums.CURRENCY;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChargeNewCardDTO {
    private int amount;
    private CURRENCY currency;
    private CustomerCardDTO cardInfo;
    private CustomerDTO customerInfo;
}
