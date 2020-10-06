package com.sqli.paymentservice.service.iservice;

import org.springframework.stereotype.Service;

import com.sqli.paymentservice.dto.ChargeCustomerCardDto;
import com.sqli.paymentservice.dto.ChargeNewCardDTO;
import com.stripe.model.Charge;

@Service
public interface IStripeChargeService {
    public Charge chargeNewCard(ChargeNewCardDTO chargeNewCardDTO) throws Exception;
    public Charge chargeCustomerCard(ChargeCustomerCardDto chargeCustomerCardDto) throws Exception;
}
