package com.sqli.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCardDTO {
    private String cardNumber;
    private Integer exp_month;
    private Integer exp_year;
    private Integer cvc;
}
