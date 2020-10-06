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

import com.sqli.checkoutservice.dtos.ShippingDTO;

@Service
public class ShippingCreaterService {

    @Value("${shipping.url}")
    private String shippingUrl;

    private final Logger logger;

    private final RestTemplate restTemplate;

    public ShippingCreaterService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        logger = LoggerFactory.getLogger(PaymentPreOrderService.class);
    }

    public boolean createShipping(ShippingDTO shipping){
        logger.info("send shipping info was starting");
        String shippingsUrl=shippingUrl+"/shipping";
        logger.info("shippings "+shippingsUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ShippingDTO> requestEntity = new HttpEntity<>(shipping, headers);
        ResponseEntity<ShippingDTO> response = restTemplate.exchange(shippingsUrl, HttpMethod.POST, requestEntity,ShippingDTO.class);
        logger.info("shipping info was sent succefully");
        return response.getStatusCode().equals(HttpStatus.OK);
    }
}
