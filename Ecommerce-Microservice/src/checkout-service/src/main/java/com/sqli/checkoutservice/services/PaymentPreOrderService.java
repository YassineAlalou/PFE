package com.sqli.checkoutservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sqli.checkoutservice.dtos.payment.PaymentDTO;

@Service
public class PaymentPreOrderService {

    @Value("${payment.url}")
    private String paymentUrl;

    private final Logger logger;

    private final RestTemplate restTemplate;

    public PaymentPreOrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        logger = LoggerFactory.getLogger(PaymentPreOrderService.class);
    }

    public boolean PayOrderCart(PaymentDTO payment){
        logger.info("start payment process ");
        HttpHeaders headers = new HttpHeaders();
        logger.info(paymentUrl);
        String paymentCharge=paymentUrl+"/payment/new-card/charge";
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println(paymentUrl);
        HttpEntity<PaymentDTO> requestEntity = new HttpEntity<>(payment, headers);
        ResponseEntity<Object> response = restTemplate.exchange(paymentCharge, HttpMethod.POST, requestEntity,Object.class);
        logger.info("payment of "+payment.getAmount());
        return response.getStatusCode().equals(HttpStatus.OK);
    }
}
